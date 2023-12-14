export interface MemberDto{
  num?: number,
  name: string,
  familyName: string
  accessionDate: Date,
  nationality: string,
  identityDocument: 'CIN' | 'CARTE_RESIDENCE' | 'PASSPORT',
  identityNumber: string,
  rankings?: [],
  huntings?: []
}
