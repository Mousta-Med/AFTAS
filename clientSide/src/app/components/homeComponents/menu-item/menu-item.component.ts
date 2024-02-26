import {Component, Input} from '@angular/core';
import {MenuItem} from "primeng/api";
import {Route, Router} from "@angular/router";

@Component({
  selector: 'app-menu-item',
  templateUrl: './menu-item.component.html',
  styleUrls: ['./menu-item.component.scss']
})
export class MenuItemComponent {
  routeFromUrl: string;

  @Input()
  menuItem: MenuItem = {};

  constructor(
    private router: Router
  ) {
    this.routeFromUrl = this.router.url;
  }
}
