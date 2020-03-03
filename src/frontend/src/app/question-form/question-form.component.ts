import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']
})
export class QuestionsComponent implements OnInit {

  questionForm = new FormGroup({
    text: new FormControl(''),
    comment: new FormControl(''),
    added_by: new FormControl(''),
  })
  constructor() { }

  ngOnInit(): void {
  }

  onSubmit() {
    console.warn(this.questionForm.value);
  }

}
