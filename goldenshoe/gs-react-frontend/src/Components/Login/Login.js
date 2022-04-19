import React, {useEffect} from "react";
import {Field, Form, Formik} from "formik";
import * as Yup from "yup";
import {toast, ToastContainer} from "react-toastify";
import {Col, Container, Row} from "react-bootstrap";
import {Button, LinearProgress} from "@material-ui/core";
import {TextField} from "formik-material-ui";
import {useStateValue} from "../../StateProvider";
import service from "../../service/HttpService";
import {Link} from "react-router-dom";
import 'react-toastify/dist/ReactToastify.css';
import {useHistory} from "react-router";


import "./Login.css";

const LoginForm = (props) => (
    <Container className="d-flex justify-content-center">
        <ToastContainer/>
        <fieldset>
            <legend>Login</legend>
            <Form>
                <Row className="justify-content-center">
                    <Col xs={12} md={6} className="text-center p-3">
                        <label htmlFor="username">User Name:</label>
                        <Field
                            className="ms-4"
                            component={TextField}
                            name="username"
                            type="text"
                        />
                    </Col>

                    <Col xs={12} md={6} className="text-center p-3">
                        <label htmlFor="password">Password:</label>
                        <Field
                            className="ms-4"
                            component={TextField}
                            name="password"
                            type="password"
                            autoComplete="on"
                        />
                    </Col>
                </Row>
                <Row className="mt-4">
                    <Col className="d-flex justify-content-center p-3">
                        <Button
                            type="submit"
                            className="mr-5 gray"
                            onClick={props.submitForm}
                            disabled={props.isSubmitting}
                            variant="contained"
                        >
                            Sing In
                        </Button>

                        <Link to="/Register">
                            <Button
                                className="yellow"
                                type="submit"
                                variant="contained"
                            >
                                Sign up
                            </Button>
                        </Link>
                    </Col>

                    {props.isSubmitting && <LinearProgress/>}
                </Row>
            </Form>
        </fieldset>
    </Container>
);


const Login = () => {
    const [{connectedUser}, dispatch] = useStateValue();
    let history = useHistory();

    const addConnectedUser = () => {
        dispatch({
            type: 'ADD_CONNECTED_USER',
            payload: true
        })
    }
    useEffect(() => {
    }, [false]);
    return (

        <div>
            <Formik
                initialValues={{username: "", password: ""}}
                validationSchema={Yup.object({
                    username: Yup.string()
                        .max(15, "Must be 15 characters or less")
                        .required("username Required"),
                    password: Yup.string()
                        .max(20, "Must be 20 characters or less")
                        .min(4, "Must be at least 4 character")
                        .required("Password Required"),
                })}
                onSubmit={(values, actions) => {
                    service
                        .login(values)
                        .then((res) => {
                            if (res.status == 200) {
                                toast.success("Login Successfuly", {
                                    position: toast.POSITION.TOP_CENTER,
                                    autoClose: 1000
                                });
                                addConnectedUser();
                                localStorage.setItem('connected', true);
                                actions.resetForm();
                                setTimeout(() => {
                                    history.push('/')
                                }, 1000);
                            }
                        })
                        .catch((err) => {
                            console.log('erreur', err)
                            actions.setSubmitting(false);
                            actions.resetForm();
                            toast.error("Invalid Username or Password", {
                                position: toast.POSITION.TOP_CENTER,
                            });
                        });
                }}
                component={LoginForm}
            />
        </div>
    );
};

export default Login;
