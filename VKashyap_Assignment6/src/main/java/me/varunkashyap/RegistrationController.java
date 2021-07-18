/**
 * @author: Varun Kashyap
 * FileName: RegistrationController.java
 * Date: 06/28/2021
 * Description: MVC Controller/Servlet for assignment6 User Registration
 */

package me.varunkashyap;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RegistrationController")
public class RegistrationController extends HttpServlet {
    // Inject EJB
    @EJB
    Status statusBean;

    private static final long serialVersionUID = 1L;

    public RegistrationController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // create http session
        HttpSession session = request.getSession();
        Bean bean = new Bean();

        // declare variables
        String userId = "";
        String password1 = "";
        String password2 = "";
        String firstName = "";
        String lastName = "";
        String errorMessage = "";
        String ss1 = "";
        Integer ss1Int = 0;
        Integer loginAttempts = 0;
        Integer ss2Int = 0;
        Integer ss3Int = 0;
        String ss2 = "";
        String ss3 = "";
        String email = "";
        String city = "";
        String state = "";
        String loginId = "";
        String address = "";
        String postalCode = "";
        String fullAddress = "";
        String loginPassword = "";
        Integer loginPasswordCheck = 0;
        Integer loginIdCheck = 0;
        Integer userIdCheck = 0;
        Integer formCheck = 0;
        Integer passwordCheck = 0;
        Integer firstNameCheck = 0;
        Integer lastNameCheck = 0;
        Integer addressCheck = 0;
        Integer cityCheck = 0;
        Integer stateCheck = 0;
        Integer postalCodeCheck = 0;
        Integer ss1Check = 0;
        Integer ss2Check = 0;
        Integer ss3Check = 0;
        Integer ssCombined = 0;
        Integer emailCheck = 0;
        Integer dbSearchCounter = 0;
        Integer adminLoginCheck = 0;
        String[] coursesSelected;
        String statusMessage = "";
        List<String> registrarStatusList;
        List<String> registrarSelectedStatusList;
        final String STATUS = "/Status.jsp";
        final String INDEX = "/index.jsp";
        final String FORM_A = "formA.jsp";
        final String FORM_NAME = "formName";
        final String LOGIN_INVALID = "login invalid. ";
        final String ENTER_SS = "must enter number for social security. ";
        final String REGISTER_ACTION = "registerAction";
        final String STATUS_LIST = "statusList";
        final String STATUS_MESSAGE = "statusMessage";

        // Get login capacity from servlet init param
        try {
            loginAttempts = Integer.parseInt(
                    getServletConfig().getInitParameter("loginAttempts"));
        } catch (NumberFormatException e) {
            loginAttempts = 0;
        }

        // valid login
        if (request.getParameter("formName").equals("login")) {
            loginId = request.getParameter("loginId");
            if (!loginId.isBlank() && !loginId.isEmpty()) {
                if (loginId.length() < 8 || loginId.length() > 16) {
                    formCheck = 0;
                    loginIdCheck = 0;
                    errorMessage += LOGIN_INVALID;
                } else {
                    Pattern pattern = Pattern.compile("\\s");
                    Matcher matcher = pattern.matcher(loginId);
                    if (matcher.find()) {
                        formCheck = 0;
                        loginIdCheck = 0;
                        errorMessage += LOGIN_INVALID;
                    } else {
                        // check database for valid login
                        Database database = new Database();
                        database.setSearchStudent();

                        try (ResultSet result = database.getResult();) {
                            while (result.next()) {
                                String compareTo = result.getString("User_ID");
                                if (loginId.equals(compareTo)) {
                                    loginIdCheck = 1;
                                    bean.setUserId(loginId);
                                    break;
                                } else {
                                    formCheck = 0;
                                    loginIdCheck = 0;
                                }
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                            formCheck = 0;
                            loginIdCheck = 0;
                        }
                    }
                }
            } else {
                formCheck = 0;
                errorMessage += LOGIN_INVALID;
            }
            // password check
            loginPassword = request.getParameter("loginPassword");
            if (!loginPassword.isBlank() && !loginPassword.isEmpty()) {
                if (loginPassword.length() < 8 || loginPassword.length() > 16) {
                    formCheck = 0;
                    loginPasswordCheck = 0;
                    errorMessage += "password invalid. ";
                } else {
                    Pattern pattern = Pattern.compile("\\s");
                    Matcher matcher = pattern.matcher(loginPassword);
                    if (matcher.find()) {
                        formCheck = 0;
                        loginPasswordCheck = 0;
                        errorMessage += "password invalid. ";

                    } else {
                        // check database for valid password
                        Database database = new Database();
                        database.setSearchStudent();

                        try (ResultSet result = database.getResult();) {

                            while (result.next()) {
                                String compareTo = result.getString("Password");
                                if (loginPassword.equals(compareTo)) {
                                    // login and password are correct
                                    if (loginIdCheck == 1) {
                                        ResultSet validResult = result;
                                        loginPasswordCheck = 1;
                                        bean.setPassword(loginPassword);
                                        firstName = validResult
                                                .getString("FIRST_NAME");
                                        bean.setFirstName(firstName);
                                        lastName = validResult
                                                .getString("LAST_NAME");
                                        bean.setLastName(lastName);
                                        loginPasswordCheck = 1;
                                        break;
                                    } else {
                                        // password is correct, but login is not
                                        formCheck = 0;
                                        loginPasswordCheck = 0;
                                    }

                                } else {
                                    formCheck = 0;
                                    loginPasswordCheck = 0;
                                }
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                            formCheck = 0;
                            loginPasswordCheck = 0;
                        }
                    }
                }
            } else {
                formCheck = 0;
                errorMessage += "Password invalid. ";
            }
            // if login correct, then save updated info and forward back to
            // login page and update information
            if (loginPasswordCheck == 1 && loginIdCheck == 1
                    && !loginId.equals("adminLogin")) {
                formCheck = 1;
                bean.setLoginCorrect(true);
                Bean beanLogin = (Bean) session.getAttribute("form");

                if (beanLogin != null) {
                    if (beanLogin.getLoginAttempts() != null) {
                        loginAttempts = beanLogin.getLoginAttempts();
                        loginAttempts++;
                        bean.setLoginAttempts(loginAttempts);
                    } else {
                        loginAttempts = 1;
                        bean.setLoginAttempts(loginAttempts);
                    }

                } else {
                    loginAttempts = 1;
                    bean.setLoginAttempts(loginAttempts);

                }
                session.setAttribute("form", bean);
                RequestDispatcher rd = request.getRequestDispatcher(INDEX);
                rd.forward(request, response);

                // if login is Admin
            } else if (loginPasswordCheck == 1 && loginIdCheck == 1
                    && loginId.equals("adminLogin")) {
                formCheck = 1;
                adminLoginCheck = 1;
                RequestDispatcher rd = request
                        .getRequestDispatcher("Status.jsp");
                rd.forward(request, response);

            } else {
                // increment incorrect login attempt count and forward
                formCheck = 0;
                bean.setLoginCorrect(false);
                Bean beanLogin = (Bean) session.getAttribute("form");
                if (beanLogin != null) {
                    loginAttempts = beanLogin.getLoginAttempts();
                    loginAttempts++;
                    bean.setLoginAttempts(loginAttempts);
                } else {
                    loginAttempts = 1;
                    bean.setLoginAttempts(loginAttempts);
                }
                session.setAttribute("form", bean);
                RequestDispatcher rd = request.getRequestDispatcher(INDEX);
                rd.forward(request, response);
            }

        }
        // forward page accordingly after selection from wrongLogin page
        if (request.getParameter("wrongLogin") != null) {
            if (request.getParameter("wrongLogin").equals("login")) {
                bean.setLoginCorrect(null);
                bean.setFormAComplete(null);
                Bean beanLogin = (Bean) session.getAttribute("form");
                if (beanLogin != null) {
                    loginAttempts = beanLogin.getLoginAttempts();
                }
                bean.setLoginAttempts(loginAttempts);
                session.setAttribute("form", bean);
                RequestDispatcher rd = request.getRequestDispatcher(INDEX);
                rd.forward(request, response);
            } else {
                bean.setLoginCorrect(null);
                bean.setFormAComplete(null);
                Bean beanLogin = (Bean) session.getAttribute("form");
                if (beanLogin != null) {
                    loginAttempts = beanLogin.getLoginAttempts();
                }
                bean.setLoginAttempts(loginAttempts);
                session.setAttribute("form", bean);
                RequestDispatcher rd = request.getRequestDispatcher(FORM_A);
                rd.forward(request, response);
            }

        }
        // forward to formA registration
        if (request.getParameter("formName").equals("register")) {
            session.setAttribute("form", bean);
            RequestDispatcher rd = request.getRequestDispatcher(FORM_A);
            rd.forward(request, response);
        }

        // forward to formA registration
        if (request.getParameter("formName").equals("indexA")) {
            RequestDispatcher rd = request.getRequestDispatcher(FORM_A);
            rd.forward(request, response);
        }
        // forward to formB registration
        if (request.getParameter("formName").equals("indexB")) {
            RequestDispatcher rd = request.getRequestDispatcher("formB.jsp");
            rd.forward(request, response);
        }

        // check if formA submitted
        if (request.getParameter("formName").equals("formA")) {
            bean.setFormBComplete(false);

            // validate userId
            userId = request.getParameter("userId");
            if (!userId.isBlank() && !userId.isEmpty()) {
                if (userId.length() < 8 || userId.length() > 16) {
                    formCheck = 0;
                    userIdCheck = 0;
                    errorMessage += "userId is invalid length ";
                } else {
                    Pattern pattern = Pattern.compile("\\s");
                    Matcher matcher = pattern.matcher(userId);
                    if (matcher.find()) {
                        formCheck = 0;
                        userIdCheck = 0;
                        errorMessage += LOGIN_INVALID;
                    } else {
                        // check database for valid login
                        Database database = new Database();
                        database.setSearchStudent();

                        try (ResultSet result = database.getResult();) {
                            while (result.next()) {
                                String compareTo = result.getString("User_ID");
                                if (userId.equals(compareTo)) {
                                    userIdCheck = 0;
                                    formCheck = 0;
                                    errorMessage += "userId already exists. please select another userId. ";
                                    break;
                                } else {
                                    bean.setUserId(userId);
                                    userIdCheck = 1;
                                    formCheck = 1;
                                }
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                            formCheck = 0;
                            loginIdCheck = 0;
                            errorMessage += "userId entry invalid.";
                        }
                    }
                }
            } else {
                formCheck = 0;
                errorMessage += "userId entry invalid. ";
            }

            // validate password
            password1 = request.getParameter("password1");
            password2 = request.getParameter("password2");

            if ((!password1.isBlank() && !password1.isEmpty())
                    && (!password2.isBlank() && !password2.isEmpty())) {
                if (password1.equals(password2)) {
                    passwordCheck = 1;
                    bean.setPassword(password2);
                } else {
                    formCheck = 0;
                    errorMessage += "Password fields do not match. ";
                }
            } else {
                formCheck = 0;
                errorMessage += "One of the password fields was left blank. ";
            }

            // validate firstName
            firstName = request.getParameter("firstName");
            if (!firstName.isBlank() && !firstName.isEmpty()) {
                bean.setFirstName(firstName);
                firstNameCheck = 1;
            } else {
                formCheck = 0;
                errorMessage += "First name is invalid. ";
            }

            // validate lastName
            lastName = request.getParameter("lastName");
            if (!lastName.isBlank() && !lastName.isEmpty()) {
                bean.setLastName(lastName);
                lastNameCheck = 1;
            } else {
                formCheck = 0;
                errorMessage += "Last name is invalid. ";
            }

            // validate social security box1
            ss1 = request.getParameter("ss1");
            if (!ss1.isBlank() || !ss1.isEmpty()) {
                try {
                    ss1Int = Integer.parseInt(ss1);
                } catch (NumberFormatException e) {
                    errorMessage += ENTER_SS;
                    formCheck = 0;
                }
                if (ss1Int > 99 && ss1Int < 1000) {
                    bean.setSs1(ss1Int);
                    ss1Check = 1;
                } else {
                    errorMessage += "must enter 3 digits for 1st social security box. ";
                    formCheck = 0;
                }
            } else {
                formCheck = 0;
                errorMessage += "1st SS box left blank. ";
            }

            // validate social security box2
            ss2 = request.getParameter("ss2");
            if (!ss2.isBlank() && !ss2.isEmpty()) {
                try {
                    ss2Int = Integer.parseInt(ss2);
                } catch (NumberFormatException e) {
                    errorMessage += ENTER_SS;
                    formCheck = 0;
                }

                if (ss2Int > 9 && ss2Int < 100) {
                    bean.setSs2(ss2Int);
                    ss2Check = 1;
                } else {
                    errorMessage += "must enter 2 digits for 2nd social security box. ";
                    formCheck = 0;
                }
            } else {
                formCheck = 0;
                errorMessage += "2nd SS box left blank. ";
            }

            // validate social security box3
            ss3 = request.getParameter("ss3");
            if (!ss3.isBlank() && !ss3.isEmpty()) {
                try {
                    ss3Int = Integer.parseInt(ss3);
                } catch (NumberFormatException e) {
                    errorMessage += ENTER_SS;
                    formCheck = 0;
                }

                if (ss3Int > 999 && ss3Int < 10000) {
                    bean.setSs3(ss3Int);
                    ss3Check = 1;
                } else {
                    errorMessage += "must enter 4 digits for 3rd social security box. ";
                    formCheck = 0;
                }
            } else {
                formCheck = 0;
                errorMessage += "3rd SS box left blank. ";
            }

            if (ss1Check == 1 && ss2Check == 1 && ss3Check == 1) {
                try {
                    ssCombined = Integer.parseInt("" + ss1 + ss2 + ss3);
                } catch (NumberFormatException e) {
                    ssCombined = null;
                }
                bean.setSsCombined(ssCombined);
            }

            // validate email
            email = request.getParameter("email");
            if (!email.isEmpty() && !email.isBlank()) {
                Pattern pattern = Pattern.compile(
                        "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
                Matcher mat = pattern.matcher(email);

                if (mat.matches()) {
                    bean.setEmail(email);
                    emailCheck = 1;
                } else {
                    formCheck = 0;
                    errorMessage += "Email provided is not valid email address. ";
                }
            } else {
                formCheck = 0;
                errorMessage += "Email input not valid. ";
            }

            // check if form correct
            if (formCheck == 1 && userIdCheck == 1 && passwordCheck == 1
                    && firstNameCheck == 1 && lastNameCheck == 1
                    && ss1Check == 1 && ss2Check == 1 && ss3Check == 1) {
                bean.setFormAComplete(true);
                bean.setErrorMessage("");
            } else {
                bean.setFormAComplete(false);
                bean.setErrorMessage(errorMessage);
            }

            // forward responses
            if (Boolean.TRUE.equals(bean.isFormAComplete())) {
                session.setAttribute("form", bean);
                RequestDispatcher rd = request
                        .getRequestDispatcher("/formB.jsp");
                rd.forward(request, response);
            } else {
                session.setAttribute("form", bean);
                RequestDispatcher rd = request.getRequestDispatcher(INDEX);
                rd.forward(request, response);
            }
        }

        // handle formB submission
        if (request.getParameter("formName").equals("formB")) {
            // get java bean from formA
            Bean beanB = (Bean) session.getAttribute("form");
            address = request.getParameter("address");
            if (!address.isEmpty() && !address.isBlank()) {
                formCheck = 1;
                addressCheck = 1;
                beanB.setAddress(address);
            } else {
                formCheck = 0;
                addressCheck = 0;
                errorMessage += "Address input invalid ";
            }

            // validate city
            city = request.getParameter("city");
            if (!city.isEmpty() && !city.isBlank()) {
                if (city.matches("^[a-zA-Z]*$")) {
                    beanB.setCity(city);
                    formCheck = 1;
                    cityCheck = 1;
                } else {
                    formCheck = 0;
                    cityCheck = 0;
                    errorMessage += "City must only contain letters. ";
                }
            } else {
                formCheck = 0;
                cityCheck = 0;
                errorMessage += "City input invalid ";
            }

            // validate state
            state = request.getParameter("state");
            if ((!state.isEmpty() && !state.isBlank() && !state.equals("ZZ"))) {
                if (state.length() == 2) {
                    stateCheck = 1;
                    beanB.setState(state);
                } else {
                    formCheck = 0;
                    stateCheck = 0;
                    errorMessage += "State must be 2 capital letters. ";
                }
            } else {
                formCheck = 0;
                stateCheck = 0;
                errorMessage += "State input invalid. ";
            }

            // validate postalCode
            postalCode = request.getParameter("postalCode");
            if (!postalCode.isEmpty() && !postalCode.isBlank()) {
                if (postalCode.length() == 5) {
                    try {
                        beanB.setPostalCode(Integer.parseInt(postalCode));
                        postalCodeCheck = 1;
                    } catch (NumberFormatException e) {
                        formCheck = 0;
                        postalCodeCheck = 0;
                        errorMessage += "Postal Code must be a number. ";
                    }
                } else {
                    formCheck = 0;
                    postalCodeCheck = 0;
                    errorMessage += "Postal Code must be a 5 digits. ";
                }
            } else {
                formCheck = 0;
                postalCodeCheck = 0;
                errorMessage += "Postal Code input not valid. ";
            }

            if (addressCheck == 1 && cityCheck == 1 && stateCheck == 1
                    && postalCodeCheck == 1) {
                fullAddress = address + " " + city + ", " + state + " "
                        + postalCode;
                beanB.setFullAddress(fullAddress);
            }

            // check if form is correct
            if (formCheck == 1) {
                beanB.setFormBComplete(true);
                beanB.setErrorMessage("");
            } else {
                beanB.setFormBComplete(false);
                beanB.setErrorMessage(errorMessage);
            }

            // add user to database
            if (Boolean.TRUE.equals(beanB.isFormAComplete())
                    && Boolean.TRUE.equals(beanB.isFormBComplete())) {
                Database database = new Database();
                database.setSearchStudent();
                try (ResultSet result = database.getResult();
                        Statement statement = database.con.createStatement();) {

                    while (result.next()) {
                        String compareTo = result.getString("User_ID");
                        if (beanB.getUserId().equals(compareTo)) {
                            dbSearchCounter++;
                            break;
                        }
                    }
                    // insert into database
                    if (dbSearchCounter == 0) {
                        statement.executeUpdate(
                                "INSERT INTO STUDENT (User_Id, Password, First_Name, Last_Name, SSN, Email, Address)"
                                        + "VALUES ('" + beanB.getUserId()
                                        + "', '" + beanB.getPassword() + "', '"
                                        + beanB.getFirstName() + "', '"
                                        + beanB.getLastName() + "', "
                                        + beanB.getSsCombined() + ", '"
                                        + beanB.getEmail() + "', '"
                                        + beanB.getFullAddress() + "')");

                        beanB.setUserAddedToDB(true);
                        session.setAttribute("form", beanB);
                        RequestDispatcher rd = request
                                .getRequestDispatcher(INDEX);
                        rd.forward(request, response);
                    } else {
                        beanB.setUserAddedToDB(false);
                        session.setAttribute("form", beanB);
                        RequestDispatcher rd = request
                                .getRequestDispatcher(INDEX);
                        rd.forward(request, response);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    beanB.setUserAddedToDB(false);
                    formCheck = 0;
                    loginIdCheck = 0;
                    session.setAttribute("form", beanB);
                    RequestDispatcher rd = request.getRequestDispatcher(INDEX);
                    rd.forward(request, response);
                }
            } else {
                session.setAttribute("form", beanB);
                RequestDispatcher rd = request.getRequestDispatcher(INDEX);
                rd.forward(request, response);
            }
        }
        if (request.getParameter("formName").equals("signIn")) {
            Bean beanC = (Bean) session.getAttribute("form");
            beanC = null;
            session.setAttribute("form", beanC);
            RequestDispatcher rd = request.getRequestDispatcher(INDEX);
            rd.forward(request, response);
        }
        if (request.getParameter(REGISTER_ACTION) != null) {
            if (request.getParameter(REGISTER_ACTION)
                    .equals("registerToCourse")) {
                RequestDispatcher rd = request
                        .getRequestDispatcher("/registerToCourse.jsf");
                rd.forward(request, response);
            }
            if (request.getParameter(REGISTER_ACTION).equals("logout")) {
                RequestDispatcher rd = request
                        .getRequestDispatcher("/logout.jsp");
                rd.forward(request, response);
            }
        }
        // validation of status report course selection
        if (request.getParameter("formName").equals("statusReport")) {
            // if no courses select, return an arrayList of all courses
            if (request.getParameter("courseCheckbox") == null) {
                registrarStatusList = statusBean.getAllStatus();
                session.setAttribute(STATUS_LIST, registrarStatusList);
                statusMessage = statusBean.getStatusMessage();
                session.setAttribute(STATUS_MESSAGE, statusMessage);
                RequestDispatcher rd = request.getRequestDispatcher(STATUS);
                rd.forward(request, response);

            } else {
                coursesSelected = request.getParameterValues("courseCheckbox");
                registrarSelectedStatusList = new ArrayList<>();
                for (int i = 0; i < coursesSelected.length; i++) {
                    // split course_ID and then use that to call .getStatus(cid)
                    String course = coursesSelected[i];
                    String[] coursesArray = course.split("\\s+");
                    String cid = coursesArray[0];
                    registrarSelectedStatusList.add(statusBean.getStatus(cid));
                }
                statusMessage = statusBean.getStatusMessage();
                session.setAttribute(STATUS_MESSAGE, statusMessage);
                session.setAttribute(STATUS_LIST, registrarSelectedStatusList);
                RequestDispatcher rd = request.getRequestDispatcher(STATUS);
                rd.forward(request, response);
            }
        }

        // back button to make course selection again
        if (request.getParameter("formName").equals("backToStatus")) {
            session.setAttribute(STATUS_LIST, null);
            session.setAttribute(STATUS_MESSAGE, null);
            RequestDispatcher rd = request.getRequestDispatcher(STATUS);
            rd.forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
