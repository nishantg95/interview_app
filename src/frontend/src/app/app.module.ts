import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { NgSelectModule } from '@ng-select/ng-select';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { QuestionFormComponent } from './question-form/question-form.component';
import { TagsComponent } from './tags/tags.component';
import { ListOfQuestionsComponent } from './list-of-questions/list-of-questions.component';
import { QuestionViewComponent } from './question-view/question-view.component';
import { QuestionsByTagNameComponent } from './questions-by-tag-name/questions-by-tag-name.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SidebarComponent } from './sidebar/sidebar.component';


@NgModule({
  declarations: [
    AppComponent,
    QuestionFormComponent,
    TagsComponent,
    ListOfQuestionsComponent,
    QuestionViewComponent,
    QuestionsByTagNameComponent,
    PageNotFoundComponent,
    NavbarComponent,
    SidebarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    CommonModule,
    NgSelectModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
