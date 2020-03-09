import { Component, OnInit, Input } from '@angular/core';
import { TagService } from '../tag.service';
import { Observable } from 'rxjs';
import { Tag } from '../interfaces/tag';
import { Question } from '../interfaces/question';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
@Component({
  selector: 'app-tags',
  templateUrl: './tags.component.html',
  styleUrls: ['./tags.component.css']
})
export class TagsComponent implements OnInit {
  @Input() tag: Tag;
  // tag: Tag = {id: 1, name: 'new', questions: new Set<Question>()};
  constructor(private tagService: TagService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    // const name = this.route.snapshot.paramMap.get('name');
    // this.tag.name = name;
    // this.getQuestionByTagName(name);
  }
  getQuestionByTagName(name: string) {
    console.log(name);
    // this.tagService.getQuestionsByTagName()?
  }
}
