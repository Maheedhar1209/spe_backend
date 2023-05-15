import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { response } from 'express';
import { Observable } from 'rxjs';
import { Movie_Details } from '../models/Movie_Details.model';


const baseUrl = 'http://localhost:8080';
@Injectable({
  providedIn: 'root'
})
export class HomePageServiceService {

  constructor(private httpclient:HttpClient) {}
  getMovieDetails(movie_name:String){
    console.log("fdfd")
    let token = localStorage.getItem('user_token')
  if (token === null) {
    token = '';
  }
  let httpHeader = new HttpHeaders({
    'Content-Type': "application/json",
    'Authorization': token,
    'Access-Control-Allow-Origin': "*"
  })
    return this.httpclient.get(`${baseUrl}/Movie?movie_name=${movie_name}`, {headers: httpHeader})
  }
  sendmoviedetails(movie_details:any,phone_number:String){
    console.log("fdfd")
    return this.httpclient.get(`${baseUrl}/sms?phone_number=${phone_number}&movie_details=${movie_details}`,);
  }
  logout(){
    const token = localStorage.getItem('user_token');
    const user_id = localStorage.getItem('id');
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': `${token}`,
    };
    // localStorage.removeItem("user_token");
    localStorage.removeItem('user_token');
    localStorage.removeItem('id');
    localStorage.removeItem('movie_details');
    localStorage.removeItem('movie_img');
    return this.httpclient.delete(`${baseUrl}/deletenewreleases?id=${user_id}`,{headers});
  }
  
  httpHeaders = new HttpHeaders({
    'Content-Type': "application/json",
    // 'Authorization': localStorage.getItem('user_token'),
    'Access-Control-Allow-Origin': "*"
  })
  getnewreleases(){
    // const token = localStorage.getItem('user_token');

    let token = localStorage.getItem('user_token')
    let id = localStorage.getItem('id')
    if (token === null) {
      token = '';
    }
    if (id === null) {
      id ='';
    }
    let httpHeader = new HttpHeaders({
      'Content-Type': "application/json",
      'Authorization': token,
      'Access-Control-Allow-Origin': "*"
    })
    let params = new HttpParams()
                    .set('user_id', id);
    return this.httpclient.get(`${baseUrl}/newreleases`, {headers: httpHeader, params: params});
  }
  addnewrelease(movie_details:Movie_Details){
    let token = localStorage.getItem('user_token')
    if (token === null) {
      token = '';
    }
    let httpHeader = new HttpHeaders({
      'Content-Type': "application/json",
      'Authorization': token,
      'Access-Control-Allow-Origin': "*"
    })
    return this.httpclient.post(`${baseUrl}/addnewrelease`,movie_details, {headers: httpHeader});
  }
  changemoviedetails(movie_details:Movie_Details){
    return this.httpclient.post(`${baseUrl}/changemoviedetails`,movie_details);
  }
  sendOTP(phoneNumber:string) {
    console.log(phoneNumber);
    return this.httpclient.get(`${baseUrl}/user/sendOTP?phone_number=${phoneNumber}`);
}
verifyOTP(phoneNumber:string,otp:string) {
    return this.httpclient.get(`${baseUrl}/user/verifyOTP?phone_number=${phoneNumber}&otp=${otp}`);
}
getID(phoneNumber:string) {
  console.log(phoneNumber);
  let token = localStorage.getItem('user_token')
  if (token === null) {
    token = '';
  }
  let httpHeader = new HttpHeaders({
    'Content-Type': "application/json",
    'Authorization': token,
    'Access-Control-Allow-Origin': "*"
  })
  return this.httpclient.get(`${baseUrl}/user/getID?phone_number=${phoneNumber}`, {headers: httpHeader});
}

sendOTPforadmin(phoneNumber:string) {
  console.log(phoneNumber);
  return this.httpclient.get(`${baseUrl}/admin/sendOTP?phone_number=${phoneNumber}`);
}
verifyOTPforadmin(phoneNumber:string,otp:string) {
  return this.httpclient.get(`${baseUrl}/admin/verifyOTP?phone_number=${phoneNumber}&otp=${otp}`);
}
  // httpHeaders = new HttpHeaders({
  //   'Content-Type': "application/json",
  //   'Access-Control-Allow-Origin': "*"
  // })


// params = new HttpParams()
//                   .set('user_id', '1');

postMyList() {
  let token = localStorage.getItem('user_token')
  let id = " 1"
  if (token === null) {
    token = '';
  }
  // if (id === null) {
  //   id ='';
  // }
  let httpHeader = new HttpHeaders({
    'Content-Type': "application/json",
    'Authorization': token,
    'Access-Control-Allow-Origin': "*"
  })
  let params = new HttpParams()
                  .set('user_id', id);
  let url = "http://localhost:8101/MyList";
  console.log(token)
  return this.httpclient.post<any>(url, null, {params: new HttpParams()
    .set('user_id', id), headers: httpHeader});

}

postGenre(genre: string) {
  let token = localStorage.getItem('user_token')
  if (token === null) {
    token = '';
  }
  let httpHeader = new HttpHeaders({
    'Content-Type': "application/json",
    'Authorization': token,
    'Access-Control-Allow-Origin': "*"
  })
  let url ="http://localhost:8101/Genres";
  console.log("genre: ", token)
  return this.httpclient.post<any>(url, null, {headers: httpHeader, params: new HttpParams().set('genre', genre)});
}

addtomylist(movie: string) {
  let token = localStorage.getItem('user_token')
  let id = localStorage.getItem('id')
  if (token === null) {
    token = '';
  }
  if (id === null) {
    id ='';
  }
  let httpHeader = new HttpHeaders({
    'Content-Type': "application/json",
    'Authorization': token,
    'Access-Control-Allow-Origin': "*"
  })
  let params = new HttpParams()
                  .append('movie_name', movie)
                  .append('user_id', id);
  let url="http://localhost:8101/AddMyList";
  return this.httpclient.post<any>(url, null, 
    {headers: httpHeader, params: params});
}

}
