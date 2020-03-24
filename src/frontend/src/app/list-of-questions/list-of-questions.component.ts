import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { Question } from '../interfaces/question';
import { Tag } from '../interfaces/tag';
import { QuestionService } from '../question.service';
import { TagService } from '../tag.service';
import { Router } from '@angular/router';
import { EventBusService } from '../event-bus.service';
// import { SearchService } from '../search.service';

@Component({
  selector: 'app-list-of-questions',
  templateUrl: './list-of-questions.component.html',
  styleUrls: ['./list-of-questions.component.css']
})
export class ListOfQuestionsComponent implements OnInit /*, OnChanges*/ {
  @Input() state: string;
  @Input() tag: string;
 // @Input() searchTerm: string;
   searchTermK: string;
  questions: Question[] = [];
  tags: Tag[] = [];
  showPreview = true;
  toggleView = true;
  key = 'editQuestion';
  constructor(
    private questionService: QuestionService,
    private tagService: TagService,
    // private searchService: SearchService,
    private router: Router,
    private eventBusService: EventBusService
  ) {}

  ngOnInit(): void {
    this.eventBusService.on('searchTerm', (data: string ) => {  
      console.log(data)
      this.listAllQuestionswithKeywords(data)
    });
    this.getAlltags();
    if (this.state == null) {
      this.listAllQuestions();
    }
  }

  // ngOnChanges(): void {
  //   if (this.state === 'tag') {
  //     this.listAllQuestionsTagged(this.tag);
  //   } else if(this.state === 'search'){
  //     this.listAllQuestionswithKeywords(this.searchTerm);

  //   }
  // }

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

  listAllQuestionswithKeywords(searchTerm: string) {
  //  this.searchService
  //.searchByKeyword(searchTerm)
  this.questionService.searchByKeyword(searchTerm)
      .subscribe(
        questionsResponse => (this.questions = questionsResponse)
      );
  }

  getAlltags(){
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

  copyQuestionToClipboard(question: Question){
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
