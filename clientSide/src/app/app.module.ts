import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {PagenotfoundComponent} from './components/pagenotfound/pagenotfound.component';
import {HomeComponent} from './components/homeComponents/home/home.component';
import {MenuModule} from "primeng/menu";
import {DashboardComponent} from './components/homeComponents/dashboard/dashboard.component';
import {MenuBarComponent} from './components/homeComponents/menu-bar/menu-bar.component';
import {MenuItemComponent} from './components/homeComponents/menu-item/menu-item.component';
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {MemberComponent} from './components/memberComponents/member/member.component';
import {CompetitionComponent} from './components/competionComponents/competition/competition.component';
import {MenubarModule} from "primeng/menubar";
import {InputTextModule} from "primeng/inputtext";
import {RippleModule} from "primeng/ripple";
import {ButtonModule} from "primeng/button";
import {TableModule} from "primeng/table";
import {HttpClientModule} from "@angular/common/http";
import { MemberTableComponent } from './components/memberComponents/member-table/member-table.component';
import { ManageMemberComponent } from './components/memberComponents/manage-member/manage-member.component';
import {SidebarModule} from "primeng/sidebar";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {DialogModule} from "primeng/dialog";
import {ToastModule} from "primeng/toast";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {ConfirmationService, MessageService} from "primeng/api";
import { CompetitionCardComponent } from './components/competionComponents/competition-card/competition-card.component';
import { ManageCompetitionComponent } from './components/competionComponents/manage-competition/manage-competition.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { CompetitionInfoComponent } from './components/competionComponents/competition-info/competition-info.component';
import {ChipModule} from "primeng/chip";
import { RankingComponent } from './components/competionComponents/ranking/ranking.component';
import {DropdownModule} from "primeng/dropdown";

@NgModule({
  declarations: [AppComponent, PagenotfoundComponent, HomeComponent, DashboardComponent, MenuBarComponent, MenuItemComponent, MemberComponent, CompetitionComponent, MemberTableComponent, ManageMemberComponent, CompetitionCardComponent, ManageCompetitionComponent, CompetitionInfoComponent, RankingComponent],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule, ReactiveFormsModule, BrowserAnimationsModule, MenuModule, FontAwesomeModule, MenubarModule, InputTextModule, RippleModule, ButtonModule, TableModule, SidebarModule, DialogModule, ToastModule, ConfirmDialogModule, FormsModule, ChipModule, DropdownModule],
  providers: [MessageService, ConfirmationService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
