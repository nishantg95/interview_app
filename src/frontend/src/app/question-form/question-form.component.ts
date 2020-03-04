import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { QuestionService } from '../question.service';
import { Question } from '../interfaces/question';
import { Tag } from '../interfaces/tag';

@Component({
  selector: 'app-questions',
  templateUrl: './question-form.component.html',
  styleUrls: ['./question-form.component.css']
})
export class QuestionFormComponent implements OnInit {
  questions: Question[];
  tags: Set<Tag> = new Set<Tag>();
  questionForm = new FormGroup({
    id: new FormControl(''),
    title: new FormControl(''),
    body: new FormControl(''),
    comment: new FormControl(''),
    added_by: new FormControl(''),
    tags: new FormControl([
      {
        id: 3,
        name: 'Java'
      }
    ])
  });
  constructor(private questionService: QuestionService) {}

  ngOnInit(): void {}

  onSubmit() {
    console.warn(this.questionForm.value);
    const ques: Question = this.questionForm.value;
    this.questionService.addQuestion(ques).subscribe();
  }
}
