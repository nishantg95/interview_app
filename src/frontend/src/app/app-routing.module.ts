import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { QuestionFormComponent } from '../app/question-form/question-form.component';
import { TagsComponent } from '../app/tags/tags.component';


const routes: Routes = [
  {path: 'questions', component: QuestionFormComponent},
  {path: 'tags', component: TagsComponent},
  {path: '', redirectTo: 'questions', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
