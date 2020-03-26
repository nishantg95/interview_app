import { Component, OnInit } from '@angular/core';
import { QuestionService } from '../question.service';
import { FormBuilder, Validators } from '@angular/forms';
import { TagService } from '../tag.service';
import { EventBusService } from '../event-bus.service';
import { EventData } from '../interfaces/event.class';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  tagList: string[];
  keyWordList: string[] = [];
  //searchInput: string = '[dddd],werweewrwer[ddddwwww], [dd] "dfdf"ddddddd "dfdff"';

  searchForm = this.fb.group({
    query: ['', Validators.required]
  });
  showPrompt = false;
  constructor(
    private questionService: QuestionService,
    private fb: FormBuilder,
    private tagService: TagService,
    private eventBusService: EventBusService,
    private router: Router,
  ) { }

  ngOnInit(): void {
  }


  //TODO can set return type as keyword and tagList
  parseSearchInput(input: string) {
    const bracketsRegex = /(?<=\[).+?(?=\])/g;
    const quotesRegex = /"([^"]*)"/g;
    //const quotesRegex = /(["'])(?:(?=(\\?))\2.)*?\1/g;

    this.tagList = input.match(bracketsRegex); 
    let quotesMatch = quotesRegex.exec(input);

    while (quotesMatch) {
      this.keyWordList.push(quotesMatch[1]);
      quotesMatch = quotesRegex.exec(input);
    }
    console.log("the brackets matches are :", this.tagList)
    console.log("the quotation matches are :", this.keyWordList)
  }

  onSubmit() {
    let parsedSearchTerm : string;
    this.parseSearchInput(this.searchForm.get('query').value);
    parsedSearchTerm = this.keyWordList.pop();
    console.log(" inside onSubmit the parsed search term is: " + parsedSearchTerm );
    this.router.navigate(['/search', parsedSearchTerm ]);
  }
}
