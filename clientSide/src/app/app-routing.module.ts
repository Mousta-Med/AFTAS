import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PagenotfoundComponent} from "./components/pagenotfound/pagenotfound.component";
import {HomeComponent} from "./components/homeComponents/home/home.component";
import {DashboardComponent} from "./components/homeComponents/dashboard/dashboard.component";
import {CompetitionComponent} from "./components/competionComponents/competition/competition.component";
import {MemberComponent} from "./components/memberComponents/member/member.component";
import {CompetitionInfoComponent} from "./components/competionComponents/competition-info/competition-info.component";
import {LoginComponent} from "./components/homeComponents/login/login.component";
import {RegisterComponent} from "./components/homeComponents/register/register.component";
import {AccessGuardService, IsJury, IsManager, IsMember, IsSigned} from "./services/guard/access-guard.service";

const routes: Routes = [
  {
    path: "",
    component: HomeComponent,
    canActivate: [AccessGuardService],
    children: [
      {
        path:"",
        canActivate: [IsMember],
        component: DashboardComponent
      },
      {
        path:"competitions",
        canActivate: [IsJury],
        component: CompetitionComponent
      },
      {
        path:"members",
        canActivate: [IsManager],
        component: MemberComponent
      },
      {
        path:"competition/:code",
        canActivate: [IsMember],
        component: CompetitionInfoComponent
      }
    ]
  },
  {
    path: "login",
    component: LoginComponent,
    canActivate: [IsSigned]
  }
  ,
  {
    path: "register",
    canActivate: [IsSigned],
    component: RegisterComponent
  },
  {
    path: "**",
    pathMatch: "full",
    component: PagenotfoundComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
