export interface RegisterRequest {
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    verificationToken?: string; // Optional field for verification token
  }