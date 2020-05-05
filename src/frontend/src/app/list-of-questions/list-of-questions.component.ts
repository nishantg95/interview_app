import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { filter, map, switchMap } from 'rxjs/operators';
import { Question } from '../interfaces/question';
import { Tag } from '../interfaces/tag';
import { QuestionService } from '../question.service';
import { SearchService } from '../search.service';
import { TagService } from '../tag.service';

@Component({
  selector: 'app-list-of-questions',
  templateUrl: './list-of-questions.component.html',
  styleUrls: ['./list-of-questions.component.css']
})
export class ListOfQuestionsComponent implements OnInit {
  searchQuestions$ = new BehaviorSubject<Question[]>([]);
  taggedQuestions$ = new BehaviorSubject<Question[]>([]);
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
    this.listQuestionsforHomeUrl();
    this.listQuestionsByTag();
    this.listQuestionsByKeyword();
    this.getNoContent();
  }

  listQuestionsforHomeUrl() {
    this.getUrlPath('questions').pipe(
      switchMap(() => this.questionService.listAllQuestions()))
      .subscribe(x => this.questions = x)
  }

  listQuestionsByKeyword() {
    this.getUrlPath('search').pipe(map(url => {
      console.log("the url is " + url)
      this.route.paramMap.pipe(
        switchMap((params: ParamMap) => this.searchService.searchByKeyword(params.get('searchTerm'))))
        .subscribe(x => {
          this.questions = x.body;
          this.currentHttpStatus$.next(x.status)
        });
    })).subscribe()
  }

  listQuestionsByTag() {
    this.getUrlPath('tagged').pipe(
      map(url => {
        console.log("the url is " + url)
        this.route.paramMap.pipe(
          switchMap((params: ParamMap) => this.questionService.listQuestionsByTagName(params.get('name'))))
          .subscribe(x => this.questions = x);
      })).subscribe();
  }

  getNoContent() {
    this.currentHttpStatus$.pipe(
      filter(status => status === 204)).subscribe((x) => {
        console.log("the status is " + x)
        this.questions = []
      })
  }

  getUrlPath(path: string) {
    return this.currentUrl$.pipe(
      filter(url => url === path))
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
