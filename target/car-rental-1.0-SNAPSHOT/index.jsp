<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Main</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>

<body>
<jsp:include page="WEB-INF/views/parts/_header.jsp"></jsp:include>
<section class="h-100">
    <div class="container h-100">
        <div class="row justify-content-sm-center h-100">
            <div class="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
                <div class="text-center my-5">
                    <img src="https://getbootstrap.com/docs/5.0/assets/brand/bootstrap-logo.svg" alt="logo" width="100">
                </div>
                <div class="card shadow-lg">
                    <div class="card-body p-5">
                        <h1 class="fs-4 card-title fw-bold mb-4">Let’s find your ideal car</h1>
                        <form method="POST" action="/search_cars">
                            <div class="mb-3">
                                <label class="mb-2 text-muted">Pick-up city</label>
                                <select class="form-select">
                                    <option value="1">Kyiv</option>
                                    <option value="2">Kharkiv</option>
                                    <option value="3">Lviv</option>
                                </select>
                            </div>

                            <div class="mb-3">
                                <div class="mb-2 w-100">
                                    <label class="text-muted">Pick-up Date</label>
                                </div>
                                <input class="form-control" pattern="\d{4}-\d{2}-\d{2}" name="pickup" type="text" placeholder="2022-01-22" required>
                            </div>

                            <div class="mb-3">
                                <div class="mb-2 w-100">
                                    <label class="text-muted">Drop-off Date</label>
                                </div>
                                <input class="form-control" pattern="\d{4}-\d{2}-\d{2}" type="text" name="dropoff" placeholder="2022-01-26" aria-label="Type" required>
                            </div>

                            <div class="d-flex align-items-center">
                                <button type="submit" class="btn btn-success ms-auto">
                                    SEARCH
                                </button>
                            </div>
                        </form>
                    </div>

                </div>
                <div class="text-center mt-5 text-muted">
                    Copyright &copy; 2022 &mdash; Rental Car
                </div>
            </div>
        </div>
    </div>
</section>

<script src="js/login.js"></script>
</body>
</html>
