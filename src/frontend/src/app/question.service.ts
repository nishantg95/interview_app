import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Question } from './interfaces/question';

const endpoint = 'http://localhost:8080/api/questions';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  questions: Observable<Question[]>;

  constructor(private http: HttpClient) {}

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      // TODO: send the error to remote logging infrastructure
      alert(operation); // log to console instead

      // TODO: better job of transforming error for user consumption
      // this.log(`${operation} failed: ${error.message}`);
      // console.error('Error completing operation', operation);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  listAllQuestions(): Observable<Question[]> {
    this.questions = this.http
      .get<Question[]>(endpoint + '/getAllQuestions', httpOptions)
      .pipe(catchError(this.handleError<Question[]>('listAllQuestions', [])));
    return this.questions;
  }

  deleteQuestion(question: Question): Observable<{}> {
    const response = this.http
      .request('delete', endpoint + 'INSERT STRING HERE', { body: question })
      .pipe(
        // tap(_ => this.log(`deleted item id=${item.id}`)),
        catchError(this.handleError<Question>('deleteQuestion'))
      );
    return response;
  }
  // check if question exists
  addQuestion(question: Question): Observable<{}> {
    const response = this.http
      .post(endpoint + '/createQuestion', question)
      .pipe(
        // tap(_ => this.log(`deleted item id=${item.id}`)),
        catchError(this.handleError<Question>('addQuestion'))
      );
    return response;
  }

  updateQuestion(question: Question): Observable<{}> {
    const response = this.http
      .put(endpoint + '/updateQuestion', question, httpOptions)
      .pipe(
        // tap(_ => this.log(`deleted item id=${item.id}`)),
        catchError(this.handleError<Question>('updateQuestion'))
      );
    return response;
  }

  getQuestionById(id: number): Observable<Question>  {
    const request = this.http
      .get<Question>(endpoint + '/getQuestion/' + id)
      .pipe(
        // tap(_ => this.log(`deleted item id=${item.id}`)),
        catchError(this.handleError<Question>('getQuestionById'))
      );
    return request;
  }
}
