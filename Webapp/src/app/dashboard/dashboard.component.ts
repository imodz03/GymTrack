import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Router, NavigationExtras} from '@angular/router';
import {ExerciseService} from '../exercise-components/exercise/exercise.service';
import {StatService} from '../stat.service';
import {Exercise} from '../exercise-components/exercise/exercise';
import {Stat} from './stat';
import {Chart, ChartData, Point} from 'Chart.js';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit, AfterViewInit {

  exercises = new Array<Exercise>();
  stats = new Array<Stat>();
  selectedExercise: Exercise;

  ctx; any;
  chart: any;
  myChart: Chart;

  viewInit = false;

  constructor(private router: Router,
              private exerciseService: ExerciseService,
              private statService: StatService) { }

  ngOnInit() {
    this.getExercises();
  }


  ngAfterViewInit(): void {
    this.setupChart();
  }

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

  getExercises(): void{
    this.exerciseService.getAll().subscribe(
      resp => {
        this.exercises = resp;
      }
    );
  }

  getStat(Exid): void{
    this.statService.getStats(Exid).subscribe(
      resp => {
        this.stats = resp;
        this.displayStats();
      }
    );
  }

  displayStats(): void{

    if (this.stats.length > 0){

      this.myChart.data.labels = [];

      for (let i = 0; i < this.stats.length; i++){

        this.myChart.data.labels.push(this.stats[i].date + '-' + this.stats[i].reps + ' Rep');
        this.myChart.data.datasets.forEach((dataset) => {
          dataset.data.push(this.stats[i].weight);
        });

      }

    }

    this.myChart.update();

  }

  optionSelected(event): void{
    const select = this.exercises[event.value];
    this.selectedExercise = select;
    this.getStat(select.exerciseID);
  }

}
