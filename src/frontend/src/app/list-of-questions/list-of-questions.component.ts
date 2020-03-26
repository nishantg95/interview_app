import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { Question } from '../interfaces/question';
import { Tag } from '../interfaces/tag';
import { QuestionService } from '../question.service';
import { TagService } from '../tag.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { SearchService } from '../search.service';
import { EventBusService } from '../event-bus.service';
import { Observable, BehaviorSubject } from 'rxjs';
import { switchMap, filter, map, flatMap, tap } from 'rxjs/operators';

@Component({
  selector: 'app-list-of-questions',
  templateUrl: './list-of-questions.component.html',
  styleUrls: ['./list-of-questions.component.css']
})
export class ListOfQuestionsComponent implements OnInit {
  searchQuestions$: Observable<Question[]>;
  taggedQuestions$: Observable<Question[]>
  currentUrl$ = new BehaviorSubject<string>('');
  currentHttpStatus$ = new BehaviorSubject<number>(null);
  @Input() state: string;
  @Input() tag: string;
  questions: Question[] = [];
  tags: Tag[] = [];
  showPreview = true;
  toggleView = true;
  key = 'editQuestion';
  constructor(
    private questionService: QuestionService,
    private tagService: TagService,
    private searchService: SearchService,
    private router: Router,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.route.url.subscribe(url => {
      this.currentUrl$.next(url[0].path);
    });
    this.listQuestionsforHomeUrl().subscribe(x => this.questions = x);
    this.listQuestionsbyTag().subscribe(x => this.questions = x);
    this.listQuestionsByKeyword().subscribe(x => {
      this.questions = x.body;
      this.getNoContent().subscribe(() => this.questions = []);
    });
  }

  listQuestionsforHomeUrl() {
    return this.currentUrl$.pipe(
      filter(url => url === 'questions'),
      flatMap(() => this.questionService.listAllQuestions()))
  }

  listQuestionsByKeyword() {
    return this.route.paramMap.pipe(
      switchMap((params: ParamMap) => this.searchService.searchByKeyword(params.get('searchTerm')))
    ).pipe(
      tap(response => this.currentHttpStatus$.next(response.status)))
  }

  getNoContent() {
    return this.currentHttpStatus$.pipe(
      filter(status => status === 204))
  }

  listQuestionsbyTag() {
    return this.route.paramMap.pipe(
      switchMap((params: ParamMap) => this.questionService.listQuestionsByTagName(params.get('name')))
    );
  }

  getAlltags() {
    this.tagService.listAllTags().subscribe(tags => this.tags = tags);
  }

  compareTagName(a: Tag, b: Tag) {
    if (a.name < b.name) {
      return -1;
    }
    if (a.name > b.name) {
      return 1;
    }
    return 0;
  }

  routeToEditForm(question: Question) {
    this.router[this.key] = question;
    this.router.navigateByUrl('/editQuestion');
  }

  copyQuestionToClipboard(question: Question) {
    const selBox = document.createElement('textarea');
    selBox.style.position = 'fixed';
    selBox.style.left = '0';
    selBox.style.top = '0';
    selBox.style.opacity = '0';
    selBox.value = 'Interview Question\nTitle: ' + question.title + '\nBody: ' + question.body + '\nComments:' + question.comment;
    document.body.appendChild(selBox);
    selBox.focus();
    selBox.select();
    document.execCommand('copy');
    document.body.removeChild(selBox);
    console.log('copied');
  }
}
