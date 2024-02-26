import { Component } from '@angular/core';
import {MenuItem} from "primeng/api";
import {Router} from "@angular/router";
@Component({
  selector: 'app-menu-bar',
  templateUrl: './menu-bar.component.html',
  styleUrls: ['./menu-bar.component.scss']
})
export class MenuBarComponent {
  constructor(
    private router: Router
  ) {
  }

  menu: Array<MenuItem> = [
    {label: 'Dashboard', icon: 'pi pi-home', routerLink: '/'},
    {label: 'Competitions', icon: 'pi pi-stopwatch', routerLink: '/competitions'},
    {label: 'Members', icon: 'pi pi-user', routerLink: '/members'},
  ];
    protected readonly localStorage = localStorage;

  logOut() {
    localStorage.clear();
    this.router.navigate(['login']);
  }
}
