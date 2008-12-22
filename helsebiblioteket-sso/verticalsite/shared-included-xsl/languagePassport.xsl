<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:variable name="textTemp">
        <translations>
            <passworderror en="Password must consist of not less than 6 characters. " no="Passord må være minst 6 tegn langt. Den må inneholde både bokstaver og siffer. Spesialtegn er ikke tillatt."/>
            <required_input en="Required input" no="Påkrevd felt"/>
            <missing_or_invalid_email en="Missing or invalid format (use name@domain.com)" no="Mangler eller feil format (bruk navn@domene.no)"/>
            <my_account en="My account" no="Min konto"/>
            <login en="Login" no="Logg inn"/>
            <logout en="Logout" no="Logg ut"/>
            <logged_in_as en="You are logged in as" no="Du er logget inn som"/>
            <missing_uid_or_password en="Username or password is missing" no="Brukernavn eller passord mangler"/>
            <failed_to_authenticate en="Failed to authenticate user" no="Kunne ikke autentisere bruker"/>
            <wrong_uid_or_password en="Wrong username or password" no="Feil brukernavn eller passord"/>
            <unexpected_error en="An unexpected error occured" no="En uventet feil oppstod"/>
            <login_text en="Login" no="Logg inn"/>
            <click_to_logout en="click to logout" no="klikk for å logge ut"/>
            <username en="Username" no="Brukernavn"/>
            <password en="Password" no="Passord"/>
            <newpassword1 en="New password" no="Nytt passord"/>
            <newpassword2 en="Confirm password" no="Bekreft passord"/>
            <homepageurl en="Homepageurl" no="Hjemmeside"/>
            <phone en="Phone" no="Telefon"/>
            <mobile en="Cell phone" no="Mobil"/>
            <company en="Company" no="Firma"/>
            <remember_me en="Remember me" no="Husk meg"/>
            <click_to_login en="click to login" no="klikk for å logge inn"/>
            <register en="Register" no="Registrer deg"/>
            <forgot_your_password en="Forgot your password?" no="Glemt passord?"/>
            <passport en="Passport" no="Passport"/>
            <email_already_exists en="E-mail address already exists" no="E-postadressen finnes allerede"/>
            <user_already_exists en="The user already exists" no="Brukeren finnes allerede"/>
            <user_not_logged_in en="The user is not logged in" no="Brukeren er ikke logget inn"/>
            <user_not_found en="The user is not found" no="Brukeren kunne ikke finnes"/>
            <new_passwords_not_equal en="The new passwords do not match" no="De nye passordene er ikke like"/>
            <new_password_not_valid en="The new password is not valid" no="Det nye passordet er ikke gyldig"/>
            <join_or_leave_group_failed en="Changes in group membership failed" no="Endring av gruppemedlemskap feilet"/>
            <join_group_not_allowed en="One or more of the groups you tried to join are restricted" no="En eller flere av gruppene du forsøkte å melde deg inn i er sperret for innmelding"/>
            <account_updated_successfully en="Account updated successfully." no="Kontoen er oppdatert med de nye opplysningene."/>
            <password_changed_successfully en="Password changed successfully." no="Passordet er endret."/>
            <membership_changed_successfully en="Group membership changed successfully." no="Gruppemedlemskap er endret."/>
            <change_password_text en="Change your password." no="Endre ditt passord."/>
            <change_membership_text en="Change your group membership." no="Endre ditt gruppemedlemskap."/>
            <update_account_details_text en="Update your account details." no="Oppdater dine kontodetaljer."/>
            <password_sent en="Password sent." no="Passord er sendt."/>
            <user_created_successfully en="User created successfully." no="Ny bruker er opprettet."/>
            <register_text en="Register as new user." no="Registrer deg som ny bruker."/>
            <forgot_password_text en="Enter your e-mail address to get your password sent to you by e-mail." no="Skriv inn din e-postadresse og få passordet ditt sendt til deg i e-post."/>
            <change_password en="Change password" no="Endre passord"/>
            <old_password en="Old password" no="Gammelt passord"/>
            <new_password en="New password" no="Nytt passord"/>
            <retype_new_password en="Re-type new password" no="Gjenta nytt passord"/>
            <change en="change" no="endre"/>
            <my_groups en="My groups" no="Mine grupper"/>
            <firstname en="Firstname" no="Fornavn"/>
            <surname en="Surname" no="Etternavn"/>
            <email en="E-mail" no="E-post"/>
            <update en="update" no="oppdater"/>
            <save en="save" no="lagre"/>
            <new_user_mail_subject en="Your account information" no="Din kontoinformasjon"/>
            <new_user_mail_body en="Hi %firstname% %surname%\n\nWelcome as a new user. Here is your account information.\n\nUsername: %uid%\nPassword: %password%\n\nPlease keep this information." no="Hei %firstname% %surname%\n\nVelkommen som ny bruker. Her er kontoinformasjonen din.\n\nBrukernavn: %uid%\nPassord: %password%\n\nVennligst behold denne informasjonen."/>
            <new_user_admin_mail_subject en="User created" no="Bruker opprettet"/>
            <new_user_admin_mail_body en="Username: %uid%\nFirstname: %firstname%\nSurname: %surname%" no="Brukernavn: %uid%\nFornavn: %firstname%\nEtternavn: %surname%"/>
            <forgot_password_mail_subject en="Your password" no="Ditt passord"/>
            <forgot_password_mail_body en="Here is your password for your account. The password is reset. You can change your password on the site.\n\nUsername: %uid%\nPassword: %password%" no="Her er ditt passord. Passordet er generert på nytt. \n\nBrukernavn: %uid%\nPassord: %password%"/>
            <get_password en="Get password" no="Få passord"/>
        </translations>
    </xsl:variable>
    <xsl:variable name="textTemp2">
        <xsl:apply-templates mode="translations" select="$textTemp/translations/*"/>
    </xsl:variable>
    <xsl:variable name="translations" select="$textTemp2"/>
    <xsl:template match="*" mode="translations">
        <xsl:copy>
            <xsl:choose>
                <xsl:when test="@*[name() = $language] != ''">
                    <xsl:value-of select="@*[name() = $language]"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:text>[NOT TRANSLATED]</xsl:text>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:apply-templates/>
        </xsl:copy>
    </xsl:template>
</xsl:stylesheet>