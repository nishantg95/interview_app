import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { Question } from '../interfaces/question';
import { QuestionService } from '../question.service';
import { TagService } from '../tag.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-of-questions',
  templateUrl: './list-of-questions.component.html',
  styleUrls: ['./list-of-questions.component.css']
})
export class ListOfQuestionsComponent implements OnInit, OnChanges {
  @Input() state: string;
  @Input() tag: string;
  questions: Question[] = [];
  constructor(
    private questionService: QuestionService,
    private tagService: TagService,
    private router: Router
  ) {}

  ngOnInit(): void {
    if (this.state == null) {
      this.listAllQuestions();
    }
  }

  ngOnChanges(): void {
    if (this.state === 'tag') {
      this.listAllQuestionsTagged(this.tag);
    }
  }

  listAllQuestions(): void {
    this.questionService
      .listAllQuestions()
      .subscribe(questions => (this.questions = questions));
  }

  listAllQuestionsTagged(tag: string) {
    this.tagService
      .getTagByName(tag)
      .subscribe(
        tagResponse => (this.questions = Array.from(tagResponse.questions))
      );
  }
}
