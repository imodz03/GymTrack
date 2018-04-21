import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {Router, NavigationExtras} from '@angular/router';
import {ExerciseService} from '../exercise-components/exercise/exercise.service';
import {StatService} from '../services/stat.service';
import {Exercise} from '../exercise-components/exercise/exercise';
import {Stat} from './stat';
import {Chart, ChartData, Point} from 'Chart.js';
import {NavigationComponent} from '../navigation/navigation.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit, AfterViewInit {

  exercises = new Array<Exercise>();
  stats = new Array<Stat>();
  selectedExercise: Exercise;

  // stats chart - experimental
  ctx; any;
  chart: any;
  myChart: Chart;

  @ViewChild(NavigationComponent)navComp: NavigationComponent;

  constructor(private router: Router,
              private exerciseService: ExerciseService,
              private statService: StatService) { }

  ngOnInit() {
    this.getExercises();
  }


  ngAfterViewInit(): void {
    this.setupChart();
  }

  // finds chart and initializes the code for it
  setupChart(): void{
    this.ctx = document.getElementById('myChart');
    this.chart = this.ctx.getContext('2d');
    this.myChart = new Chart(this.ctx, {
      type: 'line',
      data: {
        labels: [],
        datasets: [{
          label: 'Weight',
          data: []
        }],
        fill: false
      },
      options: {}
    });
  }

  // pulls down exercises for selecting the stats you wish to view
  getExercises(): void{
    this.exerciseService.getAll().subscribe(
      resp => {
        this.exercises = resp;
      }
    );
  }

  // retrieves the stats for the chart - only works for weighted sets
  getStat(Exid): void{
    this.statService.getStats(Exid).subscribe(
      resp => {
        this.stats = resp;
        this.displayStats();
      }
    );
  }

  // injects the retrieved stats into the chat
  displayStats(): void{

    if (this.stats.length > 0){

      this.myChart.data.labels = [];

      for (let i = 0; i < this.stats.length; i++){

        // pushing info in to chart
        this.myChart.data.labels.push(this.stats[i].date + '-' + this.stats[i].reps + ' Rep');
        this.myChart.data.datasets.forEach((dataset) => {
          dataset.data.push(this.stats[i].weight);
        });

      }

    }

    // update chart after info has been added
    this.myChart.update();

  }

  // the user selects the exercise they want to view stats of
  optionSelected(event): void{
    const select = this.exercises[event.value];
    this.selectedExercise = select;
    this.getStat(select.exerciseID);
  }

}
