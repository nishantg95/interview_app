import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Question } from '../interfaces/question';
import { QuestionService } from '../question.service';

@Component({
  selector: 'app-list-of-questions',
  templateUrl: './list-of-questions.component.html',
  styleUrls: ['./list-of-questions.component.css']
})
export class ListOfQuestionsComponent implements OnInit {

  questions: Question[];
  constructor(private questionService: QuestionService) {
  }

  ngOnInit(): void {
    this.listAllQuestions();
  }

  listAllQuestions(): void {
    this.questionService.listAllQuestions().subscribe(questions => this.questions = questions);
  }

}
