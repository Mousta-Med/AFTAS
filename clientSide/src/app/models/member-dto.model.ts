export interface MemberDto{
  num?: number,
  name: string,
  familyName: string
  accessionDate: string,
  nationality: string,
  identityDocument: 'CIN' | 'CARTE_RESIDENCE' | 'PASSPORT',
  identityNumber: string,
  rankings?: [],
  huntings?: []
}
