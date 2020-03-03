import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { QuestionService } from '../question.service';
import { Question } from '../interfaces/question';

@Component({
  selector: 'app-questions',
  templateUrl: './question-form.component.html',
  styleUrls: ['./question-form.component.css']
})
export class QuestionFormComponent implements OnInit {

  questions: Question[];
  questionForm = new FormGroup({
    text: new FormControl(''),
    comment: new FormControl(''),
    added_by: new FormControl(''),
  });
  constructor(private questionService: QuestionService) {
   }

  ngOnInit(): void {
  }

  onSubmit() {
    console.warn(this.questionForm.value);
    this.questionService.addQuestion(this.questionForm.value);
  }

}
