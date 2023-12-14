export interface CompetitionDto {
  code: string;
  date: string;
  startTime: string;
  endTime: string;
  numberOfParticipants: number;
  location: string;
  amount: number;
  rankings?: [];
  huntings?: [];
}
