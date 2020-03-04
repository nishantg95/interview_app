import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { QuestionFormComponent } from '../app/question-form/question-form.component';
import { ListOfQuestionsComponent } from './list-of-questions/list-of-questions.component';
import { TagsComponent } from '../app/tags/tags.component';
import { QuestionViewComponent } from './question-view/question-view.component';


const routes: Routes = [
  {path: 'questions', component: ListOfQuestionsComponent},
  {path: 'addQuestion', component: QuestionFormComponent},
  {path: 'tags', component: TagsComponent},
  {path: 'question/:id', component: QuestionViewComponent},
  {path: '', redirectTo: 'questions', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
