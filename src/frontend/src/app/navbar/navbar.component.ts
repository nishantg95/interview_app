import { Component, OnInit } from '@angular/core';
import { QuestionService } from '../question.service';
import { FormBuilder, Validators } from '@angular/forms';
import { TagService } from '../tag.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  searchForm = this.fb.group({
    query: ['', Validators.required]
  });
  showPrompt = false;
  constructor(
    private questionService: QuestionService,
    private fb: FormBuilder,
    private tagService: TagService
  ) {}

  ngOnInit(): void {}

  onSubmit() {
    console.log(this.searchForm.value);
  }
}
