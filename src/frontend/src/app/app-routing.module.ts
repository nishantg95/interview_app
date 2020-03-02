import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { QuestionsComponent } from '../app/questions/questions.component';
import { TagsComponent } from '../app/tags/tags.component';


const routes: Routes = [
  {path: 'questions', component: QuestionsComponent},
  {path: 'tags', component: TagsComponent},
  { path: '', redirectTo: 'questions', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
