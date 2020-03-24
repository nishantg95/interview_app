import { Component, OnInit } from '@angular/core';
import { Tag } from '../interfaces/tag';
import { TagService } from '../tag.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  tags: Tag[] = [];

  constructor(private tagService: TagService) { }

  ngOnInit(): void {
    this.getAlltags();

  }
  /* Set the width of the sidebar to 250px and the left margin of the page content to 250px */
openNav() {
  console.log('open');
  document.getElementById('mySidebar').style.width = '250px';
  document.getElementById('main').style.marginLeft = '250px';
}

/* Set the width of the sidebar to 0 and the left margin of the page content to 0 */
closeNav() {
  console.log('close');
  document.getElementById('mySidebar').style.width = '0';
  document.getElementById('main').style.marginLeft = '0';
}
getAlltags(){
  this.tagService.listAllTags().subscribe(tags => this.tags = tags);
}


}
