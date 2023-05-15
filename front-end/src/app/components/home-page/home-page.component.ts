import { ChangeDetectionStrategy, Component, Renderer2, ElementRef, HostBinding, OnInit, SimpleChanges, Input } from '@angular/core';
// import {HttpClient, HttpHeaders } from '@angular/common/http';
import { AppComponent } from 'src/app/app.component';
//import { GoogleMapsAPIWrapper } from '@google/maps';
import { HttpClient } from '@angular/common/http';
import { first, map } from 'rxjs/operators';
import { HomePageServiceService } from 'src/app/services/home-page-service.service';
import { Movie_Details } from 'src/app/models/Movie_Details.model';
import { Route, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { New_Release_lst } from 'src/app/models/New_Release_lst.model';
import { NgStyle } from '@angular/common';
import { from, lastValueFrom } from 'rxjs';
import { waitForAsync } from '@angular/core/testing';
@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})


export class HomePageComponent {
  
  isLoaded = false;
  divElement:any;
movie_title: any;
no_of_movies:any;
my_list_images:any;
moviedetails?: Movie_Details;
movie_details = "Please enter a movie";
showElement: boolean = false;
all_movies:any;
show_no_of_movies:any;
new_release? : New_Release_lst[];
ImagePath: string = "";

//   divElement:any;
// movie_title: any;
// dummy_title: any = "dummy";
// moviedetails?: Movie_Details;
// movie_details = "Please enter a movie";
// showElement: boolean = false;
images: any = new Array();
act_images: any = new Array();
com_images: any = new Array();
new_images: any = new Array();
thrill_images: any= new Array();
sci_images: any= new Array();
// dum: any;

slidenext = "transform: translateX(0px);";

  constructor(private router:Router,private renderer: Renderer2, private el: ElementRef,private toastr: ToastrService,private httpClient:HttpClient,private homepageservice:HomePageServiceService) {}
  async newReleases() {
    this.new_images = this.homepageservice.getnewreleases();
    this.new_release = await this.new_images.toPromise();
    
    this.all_movies = "";
    this.no_of_movies = 0;
    
    this.new_release?.forEach((item: {
      id?:number ;
      movie_name?:String;
      ott_platforms?: String;
      release_date?:String;
      movie_img?: String;
     }) => {
      if (item.movie_name != undefined) {
        this.no_of_movies = this.no_of_movies + 1;
        this.all_movies += item.movie_name + ",";
      }
    });
    
    this.show_no_of_movies = this.no_of_movies;
  }
  ngOnInit() {
    this.newReleases()
    console.log("herllo")
    // this.homepageservice.getID
    // this.Genre()
    this.Genre()
    this.myList()
    // this.router.navigate(['/home'])
  }
  moviesearch(){
      console.log(this.movie_title)
      if (this.movie_title=="" || this.movie_title == null && false)
       alert("Please type a movie");
      else{
       
        this.homepageservice.getMovieDetails(this.movie_title)
        .subscribe((res:Movie_Details)=>{
        this.moviedetails=res;
        if (this.moviedetails==null)
          alert("movie not found,try another movie");
        else{
        this.movie_details="Movie name is "+this.moviedetails?.movie_name  + ".Release date is " + this.moviedetails?.release_date + ".OTT platforms are " + this.moviedetails?.ott_platforms; 
        console.log(this.moviedetails.movie_name);
        console.log(this.moviedetails.movie_img);
        localStorage.setItem("movie_details",JSON.stringify(this.movie_details));
        localStorage.setItem("movie_img", JSON.stringify(this.moviedetails));
        this.router.navigate(["popup"]);  
      }
        });
        // container?.append(wrap);
        // console.log(container);
      }
      
    }


  shownotifications(){
    console.log(this.all_movies);
    this.toastr.success(this.all_movies, 'New Movies Added!', {
      toastClass: 'toast-custom', // <-- Use the custom class here
      positionClass: 'toast-top-right',
      timeOut:5000
      
  
    });
  }
  logout(){
    this.homepageservice.logout()
    .subscribe((res:any)=>{
      console.log(res);
    });
    this.router.navigate(["/"]);
  }
  


async myList() {
  // let user_id = localStorage.getItem('id')
  this.images =  this.homepageservice.postMyList()
}

async Genre() {
  // let user_id = localStorage.getItem('id')
  this.act_images = this.homepageservice.postGenre('Action')
  this.com_images = this.homepageservice.postGenre('Comedy')
  this.thrill_images = this.homepageservice.postGenre('Thriller')
  this.sci_images = this.homepageservice.postGenre('SciFi')
}

  set (movie_name: string){
    this.movie_title = movie_name;
    this.moviesearch();
  }
  

}

