import React from "react";
import {Field, Form, Formik} from "formik";
import * as Yup from "yup";
import {Col, Container, Row} from "react-bootstrap";
import {Button, LinearProgress} from "@material-ui/core";
import {TextField} from "formik-material-ui";
import service from "../../service/HttpService";
import {toast, ToastContainer} from "react-toastify";
import {useHistory} from "react-router";

const RegisterSchema = Yup.object().shape({
    firstName: Yup.string().required("Firstname required"),
    lastName: Yup.string().required("Lastname required"),
    dob: Yup.string().required("Date required"),
    password: Yup.string().required("Password required"),
    username: Yup.string().required("User required"),
    confirmPassword: Yup.string().oneOf(
        [Yup.ref("password"), null],
        "Password must match"
    ),
    email: Yup.string().email("Invalid email").required("Email required"),
});


const RegistrationForm = (props) => (
    <Container className="d-flex justify-content-center">
        <ToastContainer/>
        <fieldset>
            <legend>Register</legend>
            <Form>
                <Row className="justify-content-center">
                    <Col sm={12} md={6} className="text-center p-3">
                        <Field
                            component={TextField}
                            name="firstName"
                            type="text"
                            label="First Name"
                        />
                    </Col>
                    <Col sm={12} md={6} className="text-center p-3">
                        <Field
                            component={TextField}
                            name="lastName"
                            type="text"
                            label="Last Name"
                        />
                    </Col>
                </Row>

                <Row className="justify-content-center">
                    <Col sm={12} md={6} className="text-center p-3">
                        <Field
                            component={TextField}
                            name="dob"
                            type="date"
                            label="Date Of Birth"
                            InputLabelProps={{
                                shrink: true,
                            }}
                        />
                    </Col>
                    <Col sm={12} md={6} className="text-center p-3">
                        <Field
                            component={TextField}
                            name="email"
                            type="email"
                            label="Email"
                        />
                    </Col>
                </Row>

                <Row className="justify-content-center">
                    <Col sm={12} md={6} className="text-center p-3">
                        <Field
                            component={TextField}
                            name="username"
                            type="text"
                            label="User Name"
                        />
                    </Col>
                    <Col sm={12} md={6} className="text-center p-3">
                    </Col>
                </Row>

                <Row className="justify-content-center">
                    <Col sm={12} md={6} className="text-center p-3">
                        <Field
                            component={TextField}
                            type="password"
                            label="Password"
                            name="password"
                        />
                    </Col>
                    <Col sm={12} md={6} className="text-center p-3">
                        <Field
                            component={TextField}
                            type="password"
                            label="Confirm Password"
                            name="confirmPassword"
                        />
                    </Col>
                </Row>

                <Row className="justify-content-center">
                    <Col className="text-center p-3">
                        <Button
                            variant="contained"
                            disabled={props.isSubmitting}
                            onClick={props.submitForm}
                            className="register-btn yellow"
                        >
                            Submit
                        </Button>
                    </Col>
                </Row>
            </Form>
            {props.isSubmitting && <LinearProgress/>}
        </fieldset>
    </Container>
);

const Register = () => {
    let history = useHistory();

    return (
        <div>
            <Formik
                initialValues={{
                    firstName: "",
                    lastName: "",
                    dob: "",
                    email: "",
                    username: "",
                    password: "",
                    confirmPassword: "",
                }}
                validationSchema={RegisterSchema}
                onSubmit={(values, actions) => {
                    service
                        .register(values)
                        .then((res) => {
                            if (res.status === 200) {
                                toast.success("Register Successful", {
                                    position: toast.POSITION.TOP_CENTER,
                                    autoClose: 1000
                                });
                                actions.resetForm();
                                actions.setSubmitting(false);
                                setTimeout(() => {
                                    history.push('/login')
                                }, 1000);
                            }
                        })
                        .catch(() => {
                            toast.error("Register Denied", {
                                position: toast.POSITION.TOP_CENTER,
                                autoClose: 2000
                            });
                            actions.resetForm();
                            actions.setSubmitting(false);
                        });
                }}
                component={RegistrationForm}
            ></Formik>
        </div>
    );
};

export default Register;
