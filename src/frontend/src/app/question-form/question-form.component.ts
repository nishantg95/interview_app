import { Component, HostListener, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Question } from '../interfaces/question';
import { Tag } from '../interfaces/tag';
import { QuestionService } from '../question.service';
import { TagService } from '../tag.service';

@Component({
  selector: 'app-questions',
  templateUrl: './question-form.component.html',
  styleUrls: ['./question-form.component.css']
})
export class QuestionFormComponent implements OnInit {
  constructor(
    private questionService: QuestionService,
    private fb: FormBuilder,
    private tagService: TagService,
    private router: Router
  ) {}
  questions: Question[];
  setOfTags: any;
  newTag: Tag = { id: null, name: null, questions: null };
  tags: Set<Tag> = new Set<Tag>();
  key = 'editQuestion';
  data = this.router[this.key];
  enableRedirect = false;
  questionForm = this.fb.group({
    id: [null],
    title: ['', Validators.required],
    body: ['', Validators.required],
    comment: [''],
    added_by: ['', Validators.required],
    tags: ['', Validators.required]
  });

  ngOnInit(): void {
    this.setOfTags = this.tagService.listAllTags();
    if (this.data != null && this.router.url === '/editQuestion') {
      this.questionForm.setValue(this.data);

      console.log(this.router.url);
    } else if (this.router.url === '/addQuestion') {
      console.log('we came to play today!');
    } else {
      this.router.navigateByUrl('/questions');
    }

  }

  onSubmit() {
    const ques: Question = this.questionForm.value;
    console.log(ques);
    this.tagService.addTags(ques.tags).subscribe(updatedTags => {
      ques.tags = updatedTags;
      if (ques.id === null) {
        this.questionService.addQuestion(ques).subscribe(() => this.router.navigateByUrl('/questions'));
      } else {
        if (ques !== this.data) {
          this.questionService.updateQuestion(ques).subscribe(() => this.router.navigateByUrl('/questions'));
        }
      }
    });
    this.router[this.key] = undefined;
  }

  @HostListener('window:beforeunload', ['$event'])
  @HostListener('window:popstate', ['$event'])
  doSomething($event) {
    if (this.questionForm.dirty === true) {
      this.router[this.key] = undefined;
      $event.returnValue = true;
    }
  }
}
