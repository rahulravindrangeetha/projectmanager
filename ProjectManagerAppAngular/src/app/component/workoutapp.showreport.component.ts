import { Component } from '@angular/core';
import { WorkoutReportService} from '../service/workoutreportservice';
import { Chart} from 'chart.js'


@Component({
  selector: 'workoutapp-displayworkouts',
  templateUrl: '../html/workoutapp.displayreport.component.html',
  styleUrls: ['../app.component.css'],
  providers: [WorkoutReportService]

})
export class WorkoutReportComponent 
{
  data: any;
  BarChart1: [];
  BarChart2: [];
  BarChart3: [];
  totalCalorieWeek : number;
  totalCalorieMonth : number;
  totalCalorieYear : number;

  
  constructor(public workoutReportService : WorkoutReportService)
  {}

  ngOnInit()
  {
    
    try
    {
    this.workoutReportService.getWorkoutReport().
    subscribe(resp=>{
      this.data=resp;
      this.loadData();
    },error=>{console.log(error,"error")});
    }
    catch(e)
    {
      console.log(e);
    }
  }
  loadData()
  {    
    this.BarChart1 = new Chart('weekCalorie',{
      type:'bar',
      data:{
        labels:["Mon","Tue","Wed","Thu","Fri","Sat","Sun"],
        datasets:[{
          label:'Calories burned in week',
          data:[this.data.calorieBurntWeek.mon,this.data.calorieBurntWeek.tue,this.data.calorieBurntWeek.wed,this.data.calorieBurntWeek.thu,this.data.calorieBurntWeek.fri,this.data.calorieBurntWeek.sat,this.data.calorieBurntWeek.sun],
          backgroundColor:[
            'rgba(255,198,213,0.2)',
            'rgba(176,175,156,0.2)',
            'rgba(165,134,187,0.2)',
            'rgba(100,121,168,0.2)',
            'rgba(121,221,191,0.2)',
            'rgba(123,231,111,0.2)',
            'rgba(198,186,200,0.2)'
          ],
          borderColor:[
            'rgba(0,0,0,0.2)',
            'rgba(0,0,0,0.2)',
            'rgba(0,0,0,0.2)',
            'rgba(0,0,0,0.2)',
            'rgba(0,0,0,0.2)',
            'rgba(0,0,0,0.2)',
            'rgba(0,0,0,0.2)'
          ],
          borderWidth:1
        }]
      },
      options:{
        title:{
          text:"Bar Chart",
          display:true
        },
        scales:{
          yAxes:[{
            ticks:{
              beginAtZero:true
            }
          }]
        }
      }
    });

    this.totalCalorieWeek=this.data.calorieBurntWeek.mon+this.data.calorieBurntWeek.tue+this.data.calorieBurntWeek.wed+this.data.calorieBurntWeek.thu+this.data.calorieBurntWeek.fri+this.data.calorieBurntWeek.sat+this.data.calorieBurntWeek.sun;



    this.BarChart2 = new Chart('monthCalorie',{
      type:'bar',
      data:{
        labels:["Week1","Week2","Week3","Week4","Week5"],
        datasets:[{
          label:'Calories burned in month',
          data:[this.data.calorieBurntMonth.week1,this.data.calorieBurntMonth.week2,this.data.calorieBurntMonth.week3,this.data.calorieBurntMonth.week4,this.data.calorieBurntMonth.week5],
          backgroundColor:[
            'rgba(255,198,213,0.2)',
            'rgba(176,175,156,0.2)',
            'rgba(165,134,187,0.2)',
            'rgba(100,121,168,0.2)',
            'rgba(121,221,191,0.2)'
          ],
          borderColor:[
            'rgba(0,0,0,0.2)',
            'rgba(0,0,0,0.2)',
            'rgba(0,0,0,0.2)',
            'rgba(0,0,0,0.2)',
            'rgba(0,0,0,0.2)'
          ],
          borderWidth:1
        }]
      },
      options:{
        title:{
          text:"Bar Chart",
          display:true
        },
        scales:{
          yAxes:[{
            ticks:{
              beginAtZero:true
            }
          }]
        }
      }
    });
this.totalCalorieMonth=this.data.calorieBurntMonth.week1+this.data.calorieBurntMonth.week2+this.data.calorieBurntMonth.week3+this.data.calorieBurntMonth.week4+this.data.calorieBurntMonth.week5;
    this.BarChart3 = new Chart('yearCalorie',{
      type:'bar',
      data:{
        labels:["1","2","3","4","5","6","7","8","9","10","11","12"],
        datasets:[{
          label:'Calories burned in year',
          data:[this.data.calorieBurntYear.one,this.data.calorieBurntYear.two,this.data.calorieBurntYear.three,this.data.calorieBurntYear.four,this.data.calorieBurntYear.five,this.data.calorieBurntYear.six,this.data.calorieBurntYear.seven,this.data.calorieBurntYear.eight,this.data.calorieBurntYear.nine,this.data.calorieBurntYear.ten,this.data.calorieBurntYear.eleven,this.data.calorieBurntYear.twelve],
          backgroundColor:[
            'rgba(255,198,213,0.2)',
            'rgba(176,175,156,0.2)',
            'rgba(165,134,187,0.2)',
            'rgba(100,121,168,0.2)',
            'rgba(121,221,191,0.2)',
            'rgba(123,231,111,0.2)',
            'rgba(198,186,200,0.2)',
            'rgba(143,145,255,0.2)',
            'rgba(69,75,112,0.2)',
            'rgba(163,192,211,0.2)',
            'rgba(51,89,98,0.2)',
            'rgba(155,198,191,0.2)'
          ],
          borderColor:[
            'rgba(0,0,0,0.2)',
            'rgba(0,0,0,0.2)',
            'rgba(0,0,0,0.2)',
            'rgba(0,0,0,0.2)',
            'rgba(0,0,0,0.2)',
            'rgba(0,0,0,0.2)',
            'rgba(0,0,0,0.2)',
            'rgba(0,0,0,0.2)',
            'rgba(0,0,0,0.2)',
            'rgba(0,0,0,0.2)',
            'rgba(0,0,0,0.2)',
            'rgba(0,0,0,0.2)'
          ],
          borderWidth:1
        }]
      },
      options:{
        title:{
          text:"Bar Chart",
          display:true
        },
        scales:{
          yAxes:[{
            ticks:{
              beginAtZero:true
            }
          }]
        }
      }
    });

    this.totalCalorieYear=this.data.calorieBurntYear.one+this.data.calorieBurntYear.two+this.data.calorieBurntYear.three+this.data.calorieBurntYear.four+this.data.calorieBurntYear.five+this.data.calorieBurntYear.six+this.data.calorieBurntYear.seven+this.data.calorieBurntYear.eight+this.data.calorieBurntYear.nine+this.data.calorieBurntYear.ten+this.data.calorieBurntYear.eleven+this.data.calorieBurntYear.twelve;


  }
 
}
