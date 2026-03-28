import { ChangeEvent, useState } from 'react';
import { useRouter } from 'next/navigation';
import { apiUrl } from '@/lib/api';

export default function Register() {

    const router = useRouter();

    const [textUsername, setTextUsername] = useState("");
    
    const handleChangeUsername = (event: ChangeEvent<HTMLInputElement>) => {
        setTextUsername(event.target.value)
    }

    const [textEmailAddress, setTextEmailAddress] = useState("");

    const [textEAMessage, setTextEAMessage] = useState("");
    
    const validateEmail = (input: string) => {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(input);
    }

    const handleChangeEmailAddress = (event: ChangeEvent<HTMLInputElement>) => {
        const isValid = validateEmail(event.target.value)
        if (isValid)
        {
            setTextEAMessage("Valid Email")
            setTextEmailAddress(event.target.value)
        }
        else
        {
            setTextEAMessage("Invalid Email Address")
            setTextEmailAddress(event.target.value)
        }
        
    }

    const [textPhoneNumber, setTextPhoneNumber] = useState("");
    
    const [textPNMessage, setTextPNMessage] = useState("");
    
    const validatePhoneNumber = (input: string) => {
        const phoneRegex = /^(?:(?:\(?(?:0(?:0|11)\)?[ \s-]?\(?|\+)44\)?[ \s-]?\(?(?:0\)?[\s-]?\(?)?|0)(?:\d{5}\)?[\s-]?\d{4,5}|\d{4}\)?[\s-]?(?:\d{5}|\d{3}[\s-]?\d{3})|\d{3}\)?[\s-]?\d{3}[\s-]?\d{3,4}|\d{2}\)?[\s-]?\d{4}[\s-]?\d{4}))(?:[\s-]?(?:x|ext\.?|\#)\d{3,4})?$/;
        return phoneRegex.test(input);
    }

    const handleChangePhoneNumber = (event: ChangeEvent<HTMLInputElement>) => {
        const isValid = validatePhoneNumber(event.target.value)
        if (isValid)
        {
            setTextPNMessage("Valid Phone Number")
            setTextPhoneNumber(event.target.value)
        }
        else
        {
            setTextPNMessage("Invalid Phone Number")
            setTextPhoneNumber(event.target.value)
        }
    }

    const [textDOB, setTextDOB] = useState("");

    const [textDOBMessage, setTextDOBMessage] = useState("");

    const checkIsOver16 = (dobString: string) => {
        if (!dobString) return false;

        const dob = new Date(dobString);
        const today = new Date();
        
        const cutoffDate = new Date(
            today.getFullYear() - 16,
            today.getMonth(),
            today.getDate()
        );

        return dob <= cutoffDate;
    };
      
    const handleChangeDOB = (event: ChangeEvent<HTMLInputElement>) => {
        if(checkIsOver16(event.target.value))
        {
            setTextDOBMessage("Valid DOB")
            setTextDOB(event.target.value)
        }
        else
        {
            setTextDOBMessage("Must be 16 or over")
            setTextDOB(event.target.value)
        }
    }

    const [textAddress, setTextAddress] = useState("");
      
    const handleChangeAddress = (event: ChangeEvent<HTMLInputElement>) => {
        setTextAddress(event.target.value)
    }

    const [textPassword1, setTextPassword1] = useState("");

    const [textPVMessage, setTextPVMessage] = useState("");

    const [textPMMessage, setTextPMMessage] = useState("");

    const validPassword = (input: string) => {
        const phoneRegex = /^(?=.*[0-9])(?=.*[!,£,$,%,^,&,*,@,_,.])(?=.{10,})/
;
        return phoneRegex.test(input);
    }

    const passwordsMatch = (pswd1: string, pswd2: string) => {
        return pswd1 == pswd2;
    }

    const [isVisibleP1, setIsVisibleP1] = useState(false);

    const [isVisibleP2, setIsVisibleP2] = useState(false);
      
    const handleChangePassword1 = (event: ChangeEvent<HTMLInputElement>) => {
        if (validPassword(event.target.value))
        {
            setTextPVMessage("Password is valid")
            setTextPassword1(event.target.value)
        }
        else
        {
            setTextPVMessage("Password is invalid, it must contain atleast one number, atleast one special character e.g. '!, £, $, %, ^, &, *, @, ~, _, .' and atleast 10 characters")
            setTextPassword1(event.target.value)
        }
    }

    const [textPassword2, setTextPassword2] = useState("");
      
    const handleChangePassword2 = (event: ChangeEvent<HTMLInputElement>) => {
        if (passwordsMatch(textPassword1, event.target.value))
        {
            setTextPMMessage("Passwords match")
            setTextPassword2(event.target.value)
        }
        else
        {
            setTextPMMessage("Passwords do not match")
            setTextPassword2(event.target.value)
        }
    }

    const [submitMessage, setSubmitMessage] = useState("");

    const handleSubmit = ( async () => {
        if (!textUsername || !textEmailAddress || !textPhoneNumber || !textDOB || !textAddress || !textPassword1 || !textPassword2) {
        setSubmitMessage("Please complete all sections before attempting to submit.");
        return;
        }

        const userData = {
        username: textUsername,
        emailAddress: textEmailAddress,
        phoneNumber: textPhoneNumber,
        dateOfBirth: textDOB,
        address: textAddress,
        userRole: "COMMUNITY",
        password: textPassword1
        };

        try {
        const response = await fetch(apiUrl('/api/users/signup'), {
            method: 'POST',
            headers: {
            'Content-Type': 'application/json',
            },
            body: JSON.stringify(userData),
        });

        if (response.ok) {
            setSubmitMessage("Account created");
            router.push('/signInPage')
        } else {
            setSubmitMessage("Failed to create account");
        }
        } catch {
        setSubmitMessage("Error connecting to server");
        }

    })

    return (
        
        <div className = "flex flex-col items-center space-y-10 p-15 border-2 border-[#2F4F4F] bg-white rounded">

            <header className = "text-[#2F4F4F] text-[2rem] underline">
                Create an account:
            </header>

                <div className = "flex flex-col items-center space-y-4">

                    <label className = "text-[#2F4F4F] text-[1.4rem] underline" htmlFor = "user-input"> Enter your username: </label>
                    <input className = "border-2 border-[#2F4F4F]" id = "user-input" type = "text" value = {textUsername} onChange = {handleChangeUsername} placeholder = "Enter username..." />

                    <label className = "text-[#2F4F4F] text-[1.4rem] underline" htmlFor = "user-input"> Enter your email address: </label>
                    <input className = "border-2 border-[#2F4F4F]" id = "user-input" type = "text" value = {textEmailAddress} onChange = {handleChangeEmailAddress} placeholder = "Enter email address..." />
                    
                    <div role="alert" aria-live="assertive" className={`text-sm text-center ${textEAMessage === "Valid Email" ? "text-green-500" : "text-red-500"}`}>
                        {textEAMessage}
                    </div>

                    <label className = "text-[#2F4F4F] text-[1.4rem] underline" htmlFor = "user-input"> Enter your phone number: </label>
                    <input className = "border-2 border-[#2F4F4F]" id = "user-input" type = "text" value = {textPhoneNumber} onChange = {handleChangePhoneNumber} placeholder = "Enter phone number..." />
                    
                    <div role="alert" aria-live="assertive" className={`text-sm text-center ${textPNMessage === "Valid Phone Number" ? "text-green-500" : "text-red-500"}`}>
                        {textPNMessage}
                    </div>                   

                    <label className = "text-[#2F4F4F] text-[1.4rem] underline" htmlFor = "user-input"> Enter your date of birth: </label>
                    <input className = "border-2 border-[#2F4F4F]" id = "user-input" type = "date" value = {textDOB} onChange = {handleChangeDOB} placeholder = "Enter dd/mm/yyyy format..." />
                    
                    <div role="alert" aria-live="assertive" className={`text-sm text-center ${textDOBMessage === "Valid DOB" ? "text-green-500" : "text-red-500"}`}>
                        {textDOBMessage}
                    </div>
                    

                    <label className = "text-[#2F4F4F] text-[1.4rem] underline" htmlFor = "user-input"> Enter your address: </label>
                    <input className = "border-2 border-[#2F4F4F]" id = "user-input" type = "text" value = {textAddress} onChange = {handleChangeAddress} placeholder = "Enter the address..." />

                    <div className = "flex flex-col space-y-2 items-center">

                        <div className = "flex flex-col space-y-2 items-center">
                            
                            <label className = "text-[#2F4F4F] text-[1.4rem] underline" htmlFor = "user-input"> Enter your password: </label>
                            <input className = "border-2 border-[#2F4F4F]" id = "user-input" type = {isVisibleP1 ? "text" : "password"} value = {textPassword1} onChange = {handleChangePassword1} placeholder = "Enter password..." />
                            
                            <div role="alert" aria-live="assertive" className={`text-sm text-center ${textPVMessage === "Password is valid" ? "text-green-500" : "text-red-500"}`}>
                                {textPVMessage}
                            </div>
                            

                        </div>

                        <button onClick={() =>  setIsVisibleP1(!isVisibleP1)}  aria-label= {isVisibleP1 ? "Hide password" : "Show password"} type = "button" className = "flex flex-col bg-[#2F4F4F] text-white items-center p-1 border rounded hover:bg-[#263D3D] transition-colors"> <img src = "/Visibility.jpg" alt = "Hide Password Image" className = "w-7.5 h-7.5"/> </button>

                    </div>

                    <div className = "flex flex-col space-y-2 items-center">

                        <div className = "flex flex-col space-y-2 items-center">
                            
                            <label className = "text-[#2F4F4F] text-[1.4rem] underline" htmlFor = "user-input"> Enter your password again: </label>
                            <input className = "border-2 border-[#2F4F4F]" id = "user-input" type = {isVisibleP2 ? "text" : "password"} value = {textPassword2} onChange = {handleChangePassword2} placeholder = "Enter password again..." />
                            
                            <div role="alert" aria-live="assertive" className={`text-sm text-center ${textPMMessage === "Passwords match" ? "text-green-500" : "text-red-500"}`}>
                                {textPMMessage}
                            </div>


                        </div>

                        <button onClick={() => setIsVisibleP2(!isVisibleP2) }  aria-label= {isVisibleP2 ? "Hide password" : "Show password"} type = "button" className = "flex flex-col bg-[#2F4F4F] text-white items-center p-1 border rounded hover:bg-[#263D3D] transition-colors"> <img src = "/Visibility.jpg" alt="Hide password Image"className = "w-7.5 h-7.5"/> </button>

                    </div>

                    <button onClick = {handleSubmit} className="bg-[#2F4F4F] text-white px-4 py-2 rounded hover:bg-[#263D3D] transition colours"> Create account </button>
                
                    {submitMessage}

                </div>

        </div>
    );
}
