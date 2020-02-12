import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class FileService {


  private url;
  private endurl = 'file/uploadFile';
  private recettes = 'file/upload/recettes';
  private ingredients = 'file/upload/ingredients';

  constructor(private http: HttpClient) {
    this.url = environment.urldatabase;
  }

  saveFileImage(file: File) {
    const formData: FormData = new FormData();
    formData.append('file', file);
    return this.http.post(this.url + this.endurl, formData);
  }


  loadImage(fileName: string) {
    return this.http.get(this.url + 'file/images/' + fileName);
  }
}
