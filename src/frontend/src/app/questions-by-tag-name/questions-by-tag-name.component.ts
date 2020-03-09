import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-questions-by-tag-name',
  templateUrl: './questions-by-tag-name.component.html',
  styleUrls: ['./questions-by-tag-name.component.css']
})
export class QuestionsByTagNameComponent implements OnInit {
  constructor(private route: ActivatedRoute) {}
  tag = this.route.snapshot.paramMap.get('name');
  state = 'tag';

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.tag = params.name;
    });
  }
}
