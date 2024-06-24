package com.example.bpiappapi.auth.service;

import com.example.bpiappapi.auth.email.EmailSender;
import com.example.bpiappapi.auth.model.AppUser;
import com.example.bpiappapi.auth.model.AppUserRole;
import com.example.bpiappapi.auth.model.MissingPasswordRequest;
import com.example.bpiappapi.auth.repository.AppUserRepository;
import com.example.bpiappapi.auth.security.EmailValidator;
import com.example.bpiappapi.auth.security.PasswordEncoder;
import com.example.bpiappapi.auth.security.PasswordValidator;
import com.example.bpiappapi.auth.security.token.ConfirmationToken;
import com.example.bpiappapi.auth.security.token.ConfirmationTokenService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class MissingPasswordService {

   /* private final EmailSender emailSender;
    private final EmailValidator emailValidator;

    public MissingPasswordService(EmailSender emailSender, EmailValidator emailValidator) {
        this.emailSender = emailSender;
        this.emailValidator = emailValidator;
    }

    public void sendEmailToUser(String userEmail) {
        // Vérifier si l'email est valide
        if (!emailValidator.test(userEmail)) {
            throw new IllegalArgumentException("Adresse e-mail invalide");
        }

        // Envoyer un email contenant l'email de l'utilisateur à l'adresse spécifiée
        String subject = "Demande de mot de passe oublié";
        String message = "L'email de l'utilisateur est : " + userEmail;
        emailSender.sendEmail(userEmail, subject + "\n" + message);
    }*/

    private final EmailSender emailSender;
    private final EmailValidator emailValidator;
    private final AppUserService appUserService;
    private final ConfirmationTokenService confirmationTokenService;

    private final AppUserRepository appUserRepository;


    private final PasswordEncoder passwordEncoder;

    public MissingPasswordService(AppUserRepository appUserRepository,EmailSender emailSender, EmailValidator emailValidator, AppUserService appUserService, ConfirmationTokenService confirmationTokenService , PasswordEncoder passwordEncoder) {
        this.emailSender = emailSender;
        this.emailValidator = emailValidator;
        this.appUserService = appUserService;
        this.confirmationTokenService = confirmationTokenService;
        this.passwordEncoder = passwordEncoder;
        this.appUserRepository = appUserRepository;


    }

    public void sendEmailToUser(String userEmail) {
        // Vérifier si l'email est valide
        if (!emailValidator.test(userEmail)) {
            throw new IllegalArgumentException("Adresse e-mail invalide");
        }

        // Vérifier si l'utilisateur existe et est vérifié
        Optional<AppUser> optionalUser = appUserService.findVerifiedUserByEmail(userEmail);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("Adresse e-mail non vérifiée ou inconnue");
        }

        // Générer un nouveau token pour l'utilisateur
        AppUser user = optionalUser.get();
        ConfirmationToken token = new ConfirmationToken(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                user
        );
        confirmationTokenService.saveConfirmationToken(token);
         String TOKEN= token.getToken();

        // Envoyer un email contenant le lien d'activation avec le nouveau token
      String activationLink = "http://localhost:4200/reset-password/" + TOKEN;
              //+ token.getToken();
        String subject = "<div><p>Réinitialisation de mot de passe</p></div>";
        String message =
              /* "<p>Bonjour " + user.getFirstName() + ",</p>,\n\n" +
                "<p>Veuillez cliquer sur le lien ci-dessous pour continuer:</p>\n\n" +
                activationLink + "\n\n" +
                "<p>Ce lien expire dans 1 heure.</p>\n\n" +
                "<p>Cordialement,</p>\n" +
                "<p>Votre équipe de support</p>";*/

                "<div style='font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c'>\n" +
                        "\n" +
                        "<span style='display:none;font-size:1px;color:#fff;max-height:0'></span>\n" +
                        "\n" +
                        "<table role='presentation' width='100%' style='border-collapse:collapse;min-width:100%;width:100%!important' cellpadding='0' cellspacing='0' border='0'>\n" +
                        "  <tbody>\n" +
                        "    <tr>\n" +
                        "      <td width='100%' height='53' bgcolor='#0b0c0c'>\n" +
                        "        <table role='presentation' width='100%' style='border-collapse:collapse;max-width:580px' cellpadding='0' cellspacing='0' border='0' align='center'>\n" +
                        "          <tbody>\n" +
                        "            <tr>\n" +
                        "              <td width='70' bgcolor='#0b0c0c' valign='middle'>\n" +
                        "                <table role='presentation' cellpadding='0' cellspacing='0' border='0' style='border-collapse:collapse'>\n" +
                        "                  <tbody>\n" +
                        "                    <tr>\n" +
                        "                      <td style='padding-left:10px'>\n" +
                        "                    \n" +
                        "                      </td>\n" +
                        "                      <td style='font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px'>\n" +
                        "                        <span style='font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block'>Confirm your email</span>\n" +
                        "                      </td>\n" +
                        "                    </tr>\n" +
                        "                  </tbody>\n" +
                        "                </table>\n" +
                        "              </td>\n" +
                        "            </tr>\n" +
                        "          </tbody>\n" +
                        "        </table>\n" +
                        "      </td>\n" +
                        "    </tr>\n" +
                        "  </tbody>\n" +
                        "</table>\n" +
                        "<table role='presentation' class='m_-6186904992287805515content' align='center' cellpadding='0' cellspacing='0' border='0' style='border-collapse:collapse;max-width:580px;width:100%!important' width='100%'>\n" +
                        "  <tbody>\n" +
                        "    <tr>\n" +
                        "      <td width='10' height='10' valign='middle'></td>\n" +
                        "      <td>\n" +
                        "        <table role='presentation' width='100%' cellpadding='0' cellspacing='0' border='0' style='border-collapse:collapse'>\n" +
                        "          <tbody>\n" +
                        "            <tr>\n" +
                        "              <td bgcolor='#1D70B8' width='100%' height='10'></td>\n" +
                        "            </tr>\n" +
                        "          </tbody>\n" +
                        "        </table>\n" +
                        "      </td>\n" +
                        "      <td width='10' valign='middle' height='10'></td>\n" +
                        "    </tr>\n" +
                        "  </tbody>\n" +
                        "</table>\n" +
                        "<table role='presentation' class='m_-6186904992287805515content' align='center' cellpadding='0' cellspacing='0' border='0' style='border-collapse:collapse;max-width:580px;width:100%!important' width='100%'>\n" +
                        "  <tbody>\n" +
                        "    <tr>\n" +
                        "      <td height='30'><br></td>\n" +
                        "    </tr>\n" +
                        "    <tr>\n" +
                        "      <td width='10' valign='middle'><br></td>\n" +
                        "      <td style='font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px'>\n" +
                        "        <p style='Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c'>Bonjour " + user.getFirstName() + ",</p>\n" +
                        "        <p style='Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c'>Veuillez cliquer sur le lien ci-dessous pour continuer :</p>\n" +
                        "        <p style='Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c'>" + activationLink + "</p>\n" +
                        "        <p style='Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c'>Ce lien expire dans 1 heure.</p>\n" +
                        "        <p style='Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c'>Cordialement,</p>\n" +
                        "        <p style='Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c'>Votre équipe de support</p>\n" +
                        "      </td>\n" +
                        "      <td width='10' valign='middle'><br></td>\n" +
                        "    </tr>\n" +
                        "    <tr>\n" +
                        "      <td height='30'><br></td>\n" +
                        "    </tr>\n" +
                        "  </tbody>\n" +
                        "</table>\n" +
                        "<div class='yj6qo'></div><div class='adL'></div>\n" +
                        "</div>";


        emailSender.sendEmail(userEmail, subject + "\n\n" + message);

    }



    public void resetPassword(String token, String newPassword) {
        // Vérifier si le token est valide
        Optional <ConfirmationToken> confirmationToken = confirmationTokenService.getToken(token);
        if (confirmationToken == null || confirmationToken.get().isExpired() ) {
            throw new IllegalArgumentException("Token invalide ou expiré");
        }

        // Récupérer l'utilisateur associé au token
        AppUser user = confirmationToken.get().getAppUser();
        if (user == null) {
            throw new IllegalArgumentException("Utilisateur associé au token introuvable");
        }

        // Mettre à jour le mot de passe de l'utilisateur avec le nouveau mot de passe fourni
        user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(newPassword));
        appUserRepository.save(user);

    }

}
