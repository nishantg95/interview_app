import { Tag } from './tag';

export interface Question {
 id: number;
 title: string;
 body: string;
 comment: string;
 added_by: string;
 tags: Tag[];
}
