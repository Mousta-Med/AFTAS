export interface MemberDto{
  num?: number,
  name: string,
  familyName: string
  status: 'PENDING' | 'ACCEPTED' | 'REFUSED',
  role: 'ROLE_MEMBER' | 'ROLE_JURY' | 'ROLE_MANAGER',
  accessionDate: string,
  nationality: string,
  identityDocument: 'CIN' | 'CARTE_RESIDENCE' | 'PASSPORT',
  identityNumber: string,
  rankings?: [],
  huntings?: []
}
