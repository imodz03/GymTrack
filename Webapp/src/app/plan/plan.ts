import {Workout} from '../myworkout/workout';

export class Plan {

  planID: string;
  planName: string;
  public: boolean;
  repeats: number;
  description: string;
  startDate: string;
  workout: Array<Workout>;

}
