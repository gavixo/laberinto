/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Christian-PC
 */
public class Laberinto {
    private int n;
    private int m;
    private int[][] x;
    private int unos=0;
    public Laberinto(){}
    public Laberinto(int n1,int m1,int[][] x1){
        x=x1;
        n=n1;
        m=m1;
    }
    public int[][] obtenerlab(){
        return(x);
    }
    public int obtenerunos(){
        return(unos);
    }
    public void generar(){
        int u=0;
        int e=(int)(Math.random()*(m-2))+1;
        int de=n-1;
        int iz=0;
        int up=e-1;
        int ab=m-2-e;
        int md=((n-1)/2)+1;
        int f=e;
        int c=0;
        int entrai=0;
        int y=0;
        int aux=0;
        int mov=(int)(Math.random()*(md-1))+2;
        for(int i=0;i<mov;i++){
            x[f][i+1]=1;
            if(i%2!=0){
                random(f,i+1,2);
            }
            u++;
            c=i+1;
        }
        de=de-c;
        iz=iz+c-1;
        while(de!=0){
            entrai=0;
            if(up!=0 && ab!=0){
                y=(int)(Math.random()*6)+1;
                if(y%2==0){
                    entrai=1;
                }
                else{
                    entrai=2;
                }
            }
            else if(up==0){
            //    if(ab>(f-1)){
                    mov=(int)(Math.random()*(ab))+1;
          //      }
        //        else{
      //              mov=(int)(Math.random()*((f-1+1)-ab))+ab;
    //            }
                for(int i=0;i<mov;i++){
                    x[f+(i+1)][c]=1;
                 if(i%2!=0){
                    random(f+i+1,c,1);
                  }
                    u++;
                    aux=i+1;
                }
                f=f+aux;
                ab=ab-aux;
                up=up+aux;
            }
            else  if(ab==0){
        //        if(up>(f-1)){
                    mov=(int)(Math.random()*(up))+1;
      //          }
    //            else{
  //                  mov=(int)(Math.random()*((f-1+1)-up))+up;
//                }
                for(int i=0;i<mov;i++){
                    x[f-(i+1)][c]=1;
              if(i%2!=0){
                random(f-(i+1),c,1);
            }
                    u++;
                    aux=i+1;
                }
                f=f-aux;
                up=up-aux;
                ab=ab+aux;
            }
            if(entrai==1){
                    mov=(int)(Math.random()*(ab))+1;
          //      }
        //        else{
      //              mov=(int)(Math.random()*((f-1+1)-ab))+ab;
    //            }
                for(int i=0;i<mov;i++){
                    x[f+(i+1)][c]=1;
             if(i%2!=0){
                random(f+1+i,c,1);
            }
                    u++;
                    aux=i+1;
                }
                f=f+aux;
                ab=ab-aux;
                up=up+aux;
            }
            else if(entrai==2){
                    mov=(int)(Math.random()*(up))+1;
      //          }
    //            else{
  //                  mov=(int)(Math.random()*((f-1+1)-up))+up;
//                }
                for(int i=0;i<mov;i++){
                    x[f-(i+1)][c]=1;
                         if(i%2!=0){
                random(f-(i+1),c,1);
            }
                    u++;
                    aux=i+1;
                }
                f=f-aux;
                up=up-aux;
                ab=ab+aux;
            }
            if(iz!=0){
                y=(int)(Math.random()*10)+1;
            }
            else{
                y=9;
            }
            if(y==1 || y==3 || y==7){
                //if(iz>(c-1)){
                    //mov=(int)(Math.random()*((iz+1)-(c-1)))+(c-1);
                    mov=(int)(Math.random()*(iz))+1;
              //  }
            //    else{
          //          mov=(int)(Math.random()*((c-1+1)-iz))+iz;
        //        }
                 for(int i=0;i<mov;i++){
                    x[f][c-(i+1)]=1;
                           if(i%2!=0){
                random(f,c-(i+1),2);
            }
                    u++;
                    aux=i+1;
                }
                iz=iz-aux;
                de=de+aux;
                c=c-aux;
            }
            else{
      //          if(de>(c-1)){
                    mov=(int)(Math.random()*(de))+1;
    //            }
  //              else{
//                    mov=(int)(Math.random()*((c-1)-de))+de;
//                }  
                for(int i=0;i<mov;i++){
/*----*/            x[f][c+(i+1)]=1;
            if(i%2!=0){
                random(f,c+i+1,2);
            }
                    u++;
                    aux=i+1;
                }
                de=de-aux;
                iz=iz+aux;
                c=c+aux;
            }
        }
        x[e][0]=1;
        u++;
        unos=unos+u;
        //random(1,1,1);
    }
    private void random(int q,int w,int d){
        int mov=0;
        int mov1=0;
        mov=(int)(Math.random()*(100));
        mov1=(int)(Math.random()*(2));
        if(mov==2){
            int i=0;
            if(d==2){//-
                while(q>1 && i<5 && q<(m-2)){
                    x[q][w]=1;
                    unos=unos+1;
                    i++;
                    if(mov1==0){
                        q++;
                    }
                    else{
                        q--;
                    }
                }
            }
            if(d==1){//|
                while(w>1 && i<4 && w<(n-2)){
                    x[q][w]=1;
                    unos=unos+1;
                    i++;
                    if(mov1==0){
                        w++;
                    }
                    else{
                        w--;
                    }
                }
            }
        }
    }
}
