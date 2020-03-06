import { Component, OnInit } from '@angular/core';
import {
  FormGroup,
  FormControl,
  Validators,
  FormBuilder
} from '@angular/forms';
import { QuestionService } from '../question.service';
import { Question } from '../interfaces/question';
import { Tag } from '../interfaces/tag';
import { TagService } from '../tag.service';
import { Hash } from 'crypto';
import { concat } from 'rxjs';

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
    this.questionForm.valueChanges.subscribe(term => console.log(term));
  }
  checkTags(ques: Question) {
    ques.tags.forEach((tag, index) => {
      let tagToBeAdded: Tag;
      if (tag.id === undefined) {
        tagToBeAdded = { id: null, name: tag.name, questions: null };
        this.tagService.addTag(tagToBeAdded).subscribe(newTag => {
          ques.tags.splice(index, 1);
          console.log(newTag);
          ques.tags.push(newTag);
          console.log('Tag Before', tag);
          this.questionService.addQuestion(ques).subscribe();
          console.log('Tag After', ques.tags);
          // Route to the newly created question, can get id above
        });
      }
    });
  }


  onSubmit() {
    console.warn(this.questionForm.value);
    const ques: Question = this.questionForm.value;
    this.checkTags(ques);

    // let checkTag: Tag;
    // let newTags: Tag[];
    // while (ques.tags.length !== 0 ) {
    //   checkTag = ques.tags.shift();
    //   if(checkTag.id === undefined){
    //     const tagToBeAdded = { id: null, name: checkTag.name, questions: null };
    //     this.tagService.addTag(tagToBeAdded).subscribe(newTag => {
    //       console.log(newTag);
    //       ques
    //     });
    //   }
    // }
    // TODO: check if question was added successfully
  }
}

// tags: new FormControl([
//   {
//     id: 3,
//     name: 'Java'
//   }
// ])
