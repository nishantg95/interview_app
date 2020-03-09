import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { QuestionService } from '../question.service';
import { Question } from '../interfaces/question';
import { Tag } from '../interfaces/tag';
import { TagService } from '../tag.service';
import { concat, forkJoin, Observable, of } from 'rxjs';

@Component({
  selector: 'app-questions',
  templateUrl: './question-form.component.html',
  styleUrls: ['./question-form.component.css']
})
export class QuestionFormComponent implements OnInit {
  questions: Question[];
  setOfTags: any;
  tags: Set<Tag> = new Set<Tag>();
  questionForm = this.fb.group({
    id: [''],
    title: ['', Validators.required],
    body: [''],
    comment: [''],
    added_by: [''],
    tags: ['']
  });
  constructor(
    private questionService: QuestionService,
    private fb: FormBuilder,
    private tagService: TagService
  ) {}

  ngOnInit(): void {
    this.setOfTags = this.tagService.listAllTags();
    this.questionForm.valueChanges.subscribe(term => {
      if (term.tags.valueChanges) {
        console.log(term.tags.pop);
      }
    });
  }
  checkTags(ques: Question): Observable<boolean> {
    ques.tags.forEach((tag, index) => {
      let tagToBeAdded: Tag;
      if (tag.id === undefined) {
        tagToBeAdded = { id: null, name: tag.name, questions: null };
        return this.tagService.addTag(tagToBeAdded).subscribe(newTag => {
          ques.tags.splice(index, 1);
          console.log(newTag);
          ques.tags.push(newTag);
          console.log('Tag Before', tag);
          console.log('Tag After', ques.tags);
          // Route to the newly created question, can get id above
        });
      }
    });
    return of(true);
  }

  onSubmit() {
    console.warn(this.questionForm.value);
    const ques: Question = this.questionForm.value;
    this.checkTags(ques).subscribe(tagsAdded => {
      if (tagsAdded) {
        this.questionService.addQuestion(ques);
      }
    });
  }
}
