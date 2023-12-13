import { Component } from '@angular/core';
import {MenuItem} from "primeng/api";
@Component({
  selector: 'app-menu-bar',
  templateUrl: './menu-bar.component.html',
  styleUrls: ['./menu-bar.component.scss']
})
export class MenuBarComponent {

  menu: Array<MenuItem> = [
    {label: 'Dashboard', icon: 'pi pi-home', routerLink: '/'},
    {label: 'Competitions', icon: 'pi pi-stopwatch', routerLink: '/competitions'},
    {label: 'Members', icon: 'pi pi-user', routerLink: '/members'},
  ];
}
