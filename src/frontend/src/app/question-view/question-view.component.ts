import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Question } from '../interfaces/question';
import { QuestionService } from '../question.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
@Component({
  selector: 'app-question-view',
  templateUrl: './question-view.component.html',
  styleUrls: ['./question-view.component.css']
})
export class QuestionViewComponent implements OnInit {

  question: Question;
  constructor(private questionService: QuestionService,  private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.getQuestionById(+id);
  }

  getQuestionById(id: number): void {
    this.questionService.getQuestionById(id).subscribe(question => this.question = question);
  }


}
