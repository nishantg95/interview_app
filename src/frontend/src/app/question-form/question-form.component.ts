import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { QuestionService } from '../question.service';
import { Question } from '../interfaces/question';
import { Tag } from '../interfaces/tag';
import { TagService } from '../tag.service';
import { of, Observable, Subscription } from "rxjs";
import { } from "rxjs";
//import { Hash } from 'crypto';
import { concat, BehaviorSubject } from 'rxjs';
import { stringify } from 'querystring';
import { map } from 'rxjs/operators';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-questions',
  templateUrl: './question-form.component.html',
  styleUrls: ['./question-form.component.css']
})
export class QuestionFormComponent implements OnInit {
  questions: Question[];
  setOfTags: any;
  tags: Set<Tag> = new Set<Tag>();
  questionForm = this.fb.group({
    id: [''],
    title: ['', Validators.required],
    body: [''],
    comment: [''],
    added_by: [''],
    tags: ['']
  });
  constructor(
    private questionService: QuestionService,
    private fb: FormBuilder,
    private tagService: TagService
  ) { }

  ngOnInit(): void {
    this.setOfTags = this.tagService.listAllTags();
    this.questionForm.valueChanges.subscribe(term => {
      if (term.tags.valueChanges) {
        console.log(term.tags.pop);
      }
    });
  }
// addTag(tag : Tag) : Observable<any>{

// }


  observables: Observable<String>[] = [];
  saveNewTags(ques: Question) {
    let newTagCounter = 0
    let savedTagCounter = 0;
    ques.tags.forEach((tag, index) => {

      let tagToBeAdded: Tag;
      if (tag.id === undefined) {
        newTagCounter++;
        tagToBeAdded = { id: null, name: tag.name, questions: null };

        let observable = this.tagService.addTag(tagToBeAdded).pipe(map(
          newTag => {
            ques.tags.splice(index, 1);
            console.log(newTag);
            ques.tags.push(newTag);
            console.log('Tag Before', tag);
            //  this.questionService.addQuestion(ques).subscribe();
            console.log('Tag After', ques.tags);
            // return of('in progress');
            // Route to the newly created question, can get id above
            return "cool";
          },
        ));
      //  observable.subscribe();
        this.observables.push(observable);
      }
    });
    // forkJoin(observables)
    // .subscribe(dataArray => {
    //     // All observables in `observables` array have resolved and `dataArray` is an array of result of each observable
    // });
  }

  // checkIfNewTagAdded(ques: Question): Observable<boolean> {
  //   let counter: number = 0;
  //   ques.tags.forEach((tag) => {
  //     console.log("inside checkIfNewTagAdded  iteration", tag.id)
  //     if (tag.id === undefined) {
  //       counter++;
  //     }
  //   })
  //   if (counter > 0)
  //     return of(true);
  //   else
  //     return of(false);

  // }
  // saveQuestion(ques: Question) {
  // let allSaved = false;
  // this.saveNewTags(ques).subscribe({
  //   next: (x) => {
  //     allSaved = x;
  //     console.log(" inside saveQuestionWithNewTag  Next  AllSaved value is ", allSaved);
  //     this.questionService.addQuestion(ques).subscribe();
  //   },
  //   //   if (allSaved) {
  //   //     console.log("saveQuestionWithNewTag if statement. AllSaved ", allSaved);
  //   //     this.questionService.addQuestion(ques).subscribe();
  //   //   }
  //   // },
  //   complete: () => {
  //     console.log(" inside saveQuestionWithNewTag  Complete  AllSaved value is ", allSaved);
  //     this.questionService.addQuestion(ques).subscribe();
  //   }
  // });
  // else {
  //   console.log("");
  // }
  // }


  //this.questionService.addQuestion(ques).subscribe();
  // this.saveNewTags(ques).subscribe({
  //   next: (x) => console.log(x),
  //   complete: () => this.questionService.addQuestion(ques).subscribe()
  // })

  onSubmit() {
    console.warn(this.questionForm.value);
    const ques: Question = this.questionForm.value;
    this.saveNewTags(ques);
    forkJoin(this.observables).subscribe(
      x => {
        console.log("inside forkJoin x = " + x);
        this.questionService.addQuestion(ques).subscribe();
      }
    )
  }
}


    // this.checkIfNewTagAdded(ques).subscribe({
    //   next: isAdded => {
    //     if (isAdded) {
    //       console.log(" new tag check = true.  value of is added", isAdded)
    //       this.saveQuestion(ques);
    //     }
    //     else {
    //       this.questionService.addQuestion(ques).subscribe();
    //       console.log(" new tag check = false. value of is added", isAdded)
    //     }
    //   }
    // });

    // this.checkTags(ques);

    // if it exist run ch

    // 

    //

    // let checkTag: Tag;
    // let newTags: Tag[];
    // while (ques.tags.length !== 0 ) {
    //   checkTag = ques.tags.shift();
    //   if(checkTag.id === undefined){
    //     const tagToBeAdded = { id: null, name: checkTag.name, questions: null };
    //     this.tagService.addTag(tagToBeAdded).subscribe(newTag => {
    //       console.log(newTag);
    //       ques
    //     });
    //   }
    // }
    // TODO: check if question was added successfully

// tags: new FormControl([
//   {
//     id: 3,
//     name: 'Java'
//   }
// ])
