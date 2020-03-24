import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Question } from './interfaces/question';

const endpoint = 'http://localhost:8080/api/search';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};
@Injectable({
  providedIn: 'root'
})
export class SearchService {
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
      console.error(operation, error); // log to console instead

      // TODO: better job of transforming error for user consumption
      // this.log(`${operation} failed: ${error.message}`);
      // console.error('Error completing operation', operation);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  searchByKeyword(searchTerm: string): Observable<Question[]> {
    this.questions = this.http
      .get<Question[]>(endpoint + '/searchQuestions/keyword/', {
        params: { keyword: searchTerm }
      })
      .pipe(catchError(this.handleError<Question[]>('searchByKeyword', [])));
    return this.questions;
  }
}
