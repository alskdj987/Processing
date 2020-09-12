void mousePressed(){
    if(start==1){
      if(mouseX>420 && mouseX<1150){
      if(mouseY>600 && mouseY<1000){start=0;starti=start;}
      }
      if(mouseX>0 && mouseX<100){
      if(mouseY>0 && mouseY<100){start=2;starti=1;}
      }
    }
    if(start==2){
      if(mouseX>0 && mouseX<50){
         if(mouseY>0 && mouseY<50){start=1;starti=start;m=1;gravity=-9.8;damping=0;ballvx=0;ballvy=0;ballax=0;ballay=0;ballx=100;bally=100;player1x=100;player1y=830;player1v=0;player2x=1500;player2y=830;player2v=0;score1=0;score2=0;k=0;Fw=0;p=0;player1vx=10;player2vx=-10;time=0;}
      }
      
      if(mouseX>1300 && mouseX<1350){
         if(mouseY>250 && mouseY<300){m=m+0.1;}
      }
      if(mouseX>1030 && mouseX<1080){
         if(mouseY>250 && mouseY<300){
           m=m-0.1;
           if(m<0.1){m=0.1;}
        }
      }
      if(mouseX>1300 && mouseX<1350){
         if(mouseY>350 && mouseY<400){damping=damping+0.01;}
      }
      if(mouseX>1030 && mouseX<1080){
         if(mouseY>350 && mouseY<400){
           damping=damping-0.01;
           if(damping<0){damping=0;}
        }
      }
      if(mouseX>1300 && mouseX<1350){
         if(mouseY>450 && mouseY<500){gravity=gravity-0.1;}
      }
      if(mouseX>1030 && mouseX<1080){
         if(mouseY>450 && mouseY<500){
           gravity=gravity+0.1;
           if(-gravity<0.1){gravity=-0.1;}
        }
      }
      if(mouseX>1300 && mouseX<1350){
         if(mouseY>550 && mouseY<600){
         int minus=0;
         if(Fw<0){
         minus=-1;}
         Fw=abs(Fw)+0.1;
         if(minus==-1){Fw=-Fw;}
       }
      }
      if(mouseX>1030 && mouseX<1080){
         if(mouseY>550 && mouseY<600){
           int minus=0;
           if(Fw<0){minus=-1;}
           Fw=abs(Fw)-0.1;
           if(Fw<0.1){Fw=0;}
           if(minus==-1){Fw=-Fw;if(Fw==-1*0){Fw=0;}}
        }
      }
      if(mouseX>400 && mouseX<800){
         if(mouseY>700 && mouseY<900){
           if(starti==0){sure=1;}
           if(starti==1){start=1;starti=start;}
       }
      }
      if(mouseX>1000 && mouseX<1400){
         if(mouseY>700 && mouseY<900){
           if(starti==0){sure=2;}
           if(starti==1){start=1;starti=start;}
       }
      }
    }
}
