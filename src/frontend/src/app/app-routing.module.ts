import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { QuestionFormComponent } from '../app/question-form/question-form.component';
import { ListOfQuestionsComponent } from './list-of-questions/list-of-questions.component';
import { TagsComponent } from '../app/tags/tags.component';
import { QuestionViewComponent } from './question-view/question-view.component';
import { QuestionsByTagNameComponent } from './questions-by-tag-name/questions-by-tag-name.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';



const routes: Routes = [
  {path: 'questions', component: ListOfQuestionsComponent},
  {path: 'questions/tagged/:name', component: QuestionsByTagNameComponent},
  {path: 'addQuestion', component: QuestionFormComponent},
  {path: 'tags/:name', component: TagsComponent},
  {path: 'question/:id', component: QuestionViewComponent},
  {path: '', redirectTo: 'questions', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
