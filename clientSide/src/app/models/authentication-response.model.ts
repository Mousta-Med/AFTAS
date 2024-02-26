import {MemberDto} from "./member-dto.model";

export interface AuthenticationResponse{
  token: string,
  userRespDto: MemberDto
}
