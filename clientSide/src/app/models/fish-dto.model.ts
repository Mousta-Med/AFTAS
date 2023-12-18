import {HuntingDto} from "./hunting-dto.model";
import {LevelDto} from "./level-dto.model";

export interface FishDto {
  name: string;
  averageWeight: number;
  levelCode: number
  level?: LevelDto;
  huntings?: HuntingDto[];
}
