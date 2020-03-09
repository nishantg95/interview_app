import { Question } from './question';

export interface Tag {
 id: number;
 name: string;
 questions: Set<Question>;
}
