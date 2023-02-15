package aashishtathod.dev.utils.exceptions


class NoteNotFoundException(override val message: String) : Exception(message)

class UnauthorizedActivityException(override val message: String) : Exception(message)